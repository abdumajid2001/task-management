package team.bahor.repository.organization;


import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import team.bahor.entity.organization.Organization;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Override
    List<Organization> findAllById(Iterable<Long> longs);

    @Override
    Organization getById(Long aLong);

    @Override
    <S extends Organization> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Organization entity);
}
