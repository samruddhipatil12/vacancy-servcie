package org.dnyanyog.repo;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyServiceRepository extends JpaRepository<Vacancy, Long> {

  List<Vacancy> findByVacancyNameAndTenant(String vacancyName, String tenant);

  Optional<Vacancy> findByVacancyIdAndTenant(Long vacancyId, String tenant);

  List<Vacancy> findByTenant(String tenant);
}
