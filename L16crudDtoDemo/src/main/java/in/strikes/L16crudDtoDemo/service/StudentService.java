/*
|--------------------------------------------------------------------------
| STUDENT SERVICE
|--------------------------------------------------------------------------
| This class acts as the BUSINESS LAYER of the application.
|
| Request Flow:
|---------------
| Client/Postman
|        ↓
| Controller  <-- receiving data[or sending data] from[to] browser/postman/app ui
|        ↓
| Service  <-- Business Logic Lives Here
|        ↓
| Repository  <-- Database logic
|        ↓
| Database
|
| Responsibilities of Service Layer:
|-----------------------------------
| 1. Validate incoming data.
| 2. Apply business rules.
| 3. Convert DTO ↔ Entity.
| 4. Communicate with Repository.
| 5. Throw meaningful exceptions.
| 6. Prepare response object for Controller.
|
| Controller should be thin.
| Repository should only talk to DB.
| All business decisions should happen here in service layer.
|
*/

package in.strikes.L16crudDtoDemo.service;

import in.strikes.L16crudDtoDemo.dto.CreateStudentRequestDto;
import in.strikes.L16crudDtoDemo.dto.CreateStudentResponseDto;
import in.strikes.L16crudDtoDemo.dto.UpdateStudentRequestDto;
import in.strikes.L16crudDtoDemo.dto.UpdateStudentResponseDto;
import in.strikes.L16crudDtoDemo.entity.Student;
import in.strikes.L16crudDtoDemo.exception.DuplicateResourceException;
import in.strikes.L16crudDtoDemo.exception.ResourceNotFoundException;
import in.strikes.L16crudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

/*
|--------------------------------------------------------------------------
| Constructor Injection
|--------------------------------------------------------------------------
| Spring creates StudentRepository bean and injects it here.
|
| Benefits:
|---------------------------------------------
| 1. Dependency becomes mandatory.
| 2. Object becomes immutable (when field is final).
| 3. Easier unit testing.
| 4. Recommended approach by Spring team.
|
| Since StudentService has only one constructor,
| @Autowired is optional.
|--------------------------------------------------------------------------
*/

    // instance variable for repository layer
    private StudentRepository studentRepository;

    // injecting repository layer using constructor + autowire feature of spring
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

/*
|--------------------------------------------------------------------------
| createStudent() || CREATE STUDENT
|--------------------------------------------------------------------------
| Complete Flow:
|
| Request DTO  <-- coming from client side
|      ↓
| mapToEntity()  <-- so that it can be stored in database
|      ↓
| Validate Business Rules  <-- already exist email and all
|      ↓
| Save to Database  <-- service layer uses injected repository layer and calls the save method
|      ↓
| mapToDto()  <-- converting repository layer object into dto
|      ↓
| Response DTO <-- return object from repository layer has to be DTO object
|
| Method Return Type:- Create Student Response DTO object.
| Method Takes:- Request DTO object.
| Method Input:- CreateStudentRequestDto object.
| Method Output:- yes or no as per condition email found or not.
| Why Needed:- Checks Database [using repository layer] and validate email id present or not
|
| CreateStudentRequestDto
|           ↓
| mapToEntity()
|           ↓
| Student Entity
|           ↓
| emailExists()
|           ↓
| studentRepository.save()
|           ↓
| Saved Student
|           ↓
| mapToDto()
|           ↓
| CreateStudentResponseDto
|
| Simple Understanding
| Takes request data >> calls mapToEntity to convert into Entity >> Validates email >> Saves student >> Returns response dto using mapToDto method.
|--------------------------------------------------------------------------
*/

    // creating student response dto using
    // "request dto class to take request",
    // "mapToEntity method" to convert into entity,
    // "emailExists method" to validate student present or not,
    // ".save method to save in the database",
    // "mapToDto method to get final response object".
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto) {

        // Convert incoming request into database entity.
        Student student = mapToEntity(studentReqDto);

        // Business Validation. Prevent duplicate email registration.
        // Global Exception Handler can convert this exception into HTTP 409 CONFLICT.
        if(emailExists(student)) {
            throw new DuplicateResourceException("Student with email " + student.getEmail()
                    + " already exists");
        }

        Student studentResp = studentRepository.save(student);

        // Convert saved entity into response DTO.
        return mapToDto(studentResp);
    }

    public CreateStudentResponseDto getStudent(Long id) {
        Student studentResp = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("student with id " + id + " not found."));

        return mapToDto(studentResp);
    }

/*
|--------------------------------------------------------------------------
| getAllStudent() || FETCH ALL ACTIVE STUDENTS
|--------------------------------------------------------------------------
| We are implementing Soft Delete.
| Therefore:
|-------------
| deleted = false -> Visible
| deleted = true  -> Hidden
|
| Instead of finding all rows,
| fetch only active records.
|--------------------------------------------------------------------------
*/

    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();

        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

/*
|--------------------------------------------------------------------------
| updateStudent() || UPDATE EXISTING STUDENT
|--------------------------------------------------------------------------
| Steps:
|-------
| 1. Find student by id.
| 2. If not found => throw exception.
| 3. Update fields.
| 4. Update timestamp.
| 5. Save changes.
| 6. Return response DTO.
|--------------------------------------------------------------------------
*/

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        // Apply latest values received from client. Object is already managed by JPA. We are simply changing its state.
        existingStudent.setName(studentReq.getName());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }

/*
|--------------------------------------------------------------------------
| deleteStudent() || HARD DELETE
|--------------------------------------------------------------------------
| Record is physically removed from database.
|
| Example:
| Before  -->  id = 10 exists in table
| After  -->  Row completely removed.
|
| Use carefully because deleted data
| cannot be recovered easily.
|--------------------------------------------------------------------------
*/

    public void deleteStudent(Long id) {
        //Fetch student by ID.
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        // Executes SQL DELETE statement.
        studentRepository.delete(studentToBeDeleted);
    }

/*
|--------------------------------------------------------------------------
| deleteStudentSoftly() || SOFT DELETE
|--------------------------------------------------------------------------
| Instead of removing database row, we simply mark record as deleted.
|
| Advantages:
|--------------------------
| 1. Data Recovery Possible
| 2. Audit History Maintained
| 3. Safer Than Hard Delete
| 4. Used Frequently In Real Projects
|
| Database Row Still Exists
|----------------------------------
| Before  -->  id=1 , deleted=false
| After  -->  id=1 , deleted=true
|--------------------------------------------------------------------------
*/

    public void deleteStudentSoftly(Long id) {
        //Fetch active student only.
        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        // Mark record as logically deleted.
        studentToBeDeleted.setDeleted(true);

        // Persist updated state to database. Generates UPDATE query.
        studentRepository.save(studentToBeDeleted);
    }

/*
|--------------------------------------------------------------------------
| mapToEntity() || DTO → Entity Conversion Method
|--------------------------------------------------------------------------
| Purpose  -->  Convert incoming request DTO into JPA Entity.
| Why?  -->  Client should never send Entity directly.
|
| DTO is used for communication with outside world that is with client
| Entity is used for database storage. Entity represents database columns.
|
| This separation gives:
|------------------------
| 1. Better security
| 2. Loose coupling
| 3. Easier maintenance
| 4. Different request/response structures
|
| Example:
|----------
| Client sends:
| {
|   "name":"Dhaval",
|   "age":24
| }
|
| DTO receives the request.
| mapToEntity convert it to as per Entity fields
| Entity is then prepared for database insertion.
|
| CreateStudentRequestDto receives data from client
|        ↓
| mapToEntity() methods maps client data with Entity fields
|        ↓
| Entity Student sends data to repository layer
|
| Method Return Type:- Student class.
| Method Takes:- CreateStudentRequestDto object.
| Method Input:- Simple Json as per DTO object.
| Method Output:- Proper Student Class type object.
| Why Needed:- Database understand Entity [Student Class], not DTO.
|
| Controller
|     ↓
| Request DTO
|     ↓
| Service
|     ↓
| Entity
|     ↓
| Database
|--------------------------------------------------------------------------
*/

    // Client sends data, mapToEntity receives it and send it to Entity object
    private Student mapToEntity(CreateStudentRequestDto studentReqDto) {

        Student student = new Student(); // New entity object that will eventually be persisted into database.

        // Copy user-provided name into database entity.
        student.setName(studentReqDto.getName());

        // Copy age provided by client.
        student.setAge(studentReqDto.getAge());

        // Copy email which may later be used for uniqueness validation.
        student.setEmail(studentReqDto.getEmail());

        // Student roll number.
        student.setRollNo(studentReqDto.getRollNo());

        // Subject selected by student.
        student.setSubject(studentReqDto.getSubject());

        // createdAt stores when record was first created. Usually never changed later.
        student.setCreatedAt(LocalDateTime.now());

        // updatedAt stores latest modification timestamp.
        student.setUpdatedAt(LocalDateTime.now());

        // Soft Delete Flag | false -> Active Record | true  -> Deleted Record
        student.setDeleted(false);

        return student;
    }

/*
|--------------------------------------------------------------------------
| mapToDto() || Entity → Response DTO Conversion
|--------------------------------------------------------------------------
| Database Entity should not be returned directly to client.
|
| Reasons:
|----------
| 1. Hide internal fields.
| 2. Avoid exposing sensitive data.
| 3. Return only relevant information.
| 4. Keep API response contract stable.
|
| This method converts database entity into
| response format expected by API consumer.
|
| Method Return Type:- CreateStudentResponseDto DTO object.
| Method Takes:- Student class object.
| Method Input:- Student class object.
| Method Output:- Proper CreateStudentResponseDto DTO object.
| Why Needed:- We should not directly expose Entity outside.
|
| Bad - return student; Good - return responseDto;
| Because DTO allows us to control response structure.
|
| Controller
|     ↓
| Request DTO
|     ↓
| Service Layer
|     ↓
| mapToEntity
|     ↓
| Request Entity [created by maptoentity method]
|     ↓
| Database
|     ↓
| mapToDto
|     ↓
| ResponseDto for outside world [created by mapToDto method]
|--------------------------------------------------------------------------
*/

    // Entity sends data, mapToDto receives it and prepare response for client
    private CreateStudentResponseDto mapToDto(Student student) {
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        // ID is available only after database insert. Generated by database/JPA.
        responseDto.setId(student.getId());

        // Copy required fields to response.
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student saved successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;

    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student) {
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student updated successfully");
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }

/*
|--------------------------------------------------------------------------
| emailExists() || Email Existence Check
|--------------------------------------------------------------------------
| Business Rule  -->  One email should belong to only one student.
|
| Repository method  -->  existsByEmail()
|
| Spring Data JPA automatically generates SQL:
|
| SELECT EXISTS(
|    SELECT 1
|    FROM student
|    WHERE email = ?
| );
|
| Returns:
| true  -> email already present
| false -> email not present
|
| Method Return Type:- boolean [true or false | 1 or 0]
| Method Takes:- Student class object.
| Method Input:- Student class object.
| Method Output:- yes or no as per condition email found or not.
| Why Needed:- Checks Database [using repository layer] and validate email id present or not
|--------------------------------------------------------------------------
*/

    // to validate email id before saving into database
    private boolean emailExists(Student student) {
        return studentRepository.existsByEmail(student.getEmail()); // existsByEmail(String emailId); So, student.getEmail() gives email id of student
    }








}

/*
|--------------------------------------------------------------------------
| Most Important Concept To Remember
|--------------------------------------------------------------------------
| Whenever you're confused in a Spring CRUD project, ask yourself:
|
| 1. Who receives request?
|    → Controller
|
| 2. Who performs business validation?
|    → Service
|
| 3. Who talks to database?
|    → Repository
|
| 4. Who represents table?
|    → Entity
|
| 5. Who carries request/response data?
|    → DTO
|
| 6. Who converts DTO ↔ Entity?
|    → Usually Service (or Mapper classes)
|
| 7. Who throws business exceptions?
|    → Service Layer
|--------------------------------------------------------------------------
*/