package team.bahor.repository.project;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import team.bahor.entity.project.Project;
import team.bahor.repository.base.BaseGenericRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ProjectRepository extends JpaRepository<Project, Long>, BaseGenericRepository {
    @Override
    List<Project> findAll();

    @Override
    List<Project> findAllById(Iterable<Long> longs);

    @Override
    <S extends Project> List<S> saveAll(Iterable<S> entities);

    @Override
    Project getById(Long aLong);

    @Override
    <S extends Project> S save(S entity);

    @Override
    Optional<Project> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Project entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll();

    @Override
    <S extends Project> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}