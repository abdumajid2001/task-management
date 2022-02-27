package team.bahor.service.task;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import team.bahor.config.security.UserDetails;
import team.bahor.dto.task.*;
import team.bahor.entity.project.ProjectColumn;
import team.bahor.entity.task.Task;
import team.bahor.entity.task.TaskComment;
import team.bahor.mapper.task.TaskMapper;
import team.bahor.repository.column.ColumnRepository;
import team.bahor.repository.task.TaskRepository;
import team.bahor.service.base.AbstractService;
import team.bahor.validator.TaskValidator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractService<TaskRepository, TaskMapper, TaskValidator> implements TaskService {
    private final ColumnRepository columnRepository;

    public TaskServiceImpl(TaskMapper mapper, TaskValidator validator, TaskRepository repository, ColumnRepository columnRepository) {
        super(mapper, validator, repository);
        this.columnRepository = columnRepository;
    }

    @Override
    public Long create(TaskCreateDto dto) {
        Task endTaskOrder = repository.getEndTaskOrder(dto.getColumnId());
        dto.setCreatedBy(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
//        dto.setTaskOrder(Objects.isNull(endTaskOrder) ? 1 : endTaskOrder.getTaskOrder() + 1);
        Task task = mapper.fromCreateDto(dto);
        task.setCode(UUID.randomUUID().toString());
        ProjectColumn projectColumn = columnRepository.getById(dto.getColumnId());
        if(Objects.isNull(projectColumn)){
            throw  new IllegalArgumentException("not found column");
        }

//        List<Task> tasks = projectColumn.getTasks();
//        tasks.add(task);
//        columnRepository.save(projectColumn);
//        projectColumn.getTasks().add(task);
//        task.setProjectColumn(projectColumn);
//        task.setTaskComments(List.of(new TaskComment()));
        columnRepository.save(projectColumn);
//        repository.save(task);
        return null;

    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void update(TaskUpdateDto dto) {
        repository.update(dto);
    }


    @Override
    public TaskDto get(Long id) {
        Task task = (Task) repository.findByIdOfTask(id).orElseThrow(() -> new IllegalArgumentException("invalid task id" + id));
        return mapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAll(Long id) {
        List<Task> tasks = repository.getAllTasksForProjectColumn(id);
        return mapper.toDto(tasks);
    }

    public TaskUpdateDto getUpdateDto(Long id) {
        Task task = (Task) repository.findByIdOfTask(id).orElseThrow(() -> new IllegalArgumentException("invalid task id" + id));
        return mapper.toUpdateDto(task);
    }
}
