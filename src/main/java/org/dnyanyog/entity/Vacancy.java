package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "vacancy")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long vacancyId;

  @Column(name = "vacancy_name", nullable = false, length = 100)
  private String vacancyName;

  @Column(name = "job_title", nullable = false, length = 50)
  private String jobTitle;

  @Column(name = "description", length = 500)
  private String description;

  @Column(name = "hiring_manager", length = 50)
  private String hiringManager;

  @Column(name = "number_of_positions")
  private int numberOfPositions;

  @Column(name = "tenant", nullable = false, length = 50)
  private String tenant;

  public enum Status {
    OPEN,
    CLOSED,
    CANCELLED,
    ACTIVE,
    DELETED
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status = Status.OPEN;

  public Vacancy() {}

  public Vacancy(
      String vacancyName,
      String jobTitle,
      String description,
      String hiringManager,
      int numberOfPositions,
      String tenant) {
    this.vacancyName = vacancyName;
    this.jobTitle = jobTitle;
    this.description = description;
    this.hiringManager = hiringManager;
    this.numberOfPositions = numberOfPositions;
    this.tenant = tenant;
  }

  public Long getVacancyId() {
    return vacancyId;
  }

  public void setVacancyId(Long vacancyId) {
    this.vacancyId = vacancyId;
  }

  public String getVacancyName() {
    return vacancyName;
  }

  public void setVacancyName(String vacancyName) {
    this.vacancyName = vacancyName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHiringManager() {
    return hiringManager;
  }

  public void setHiringManager(String hiringManager) {
    this.hiringManager = hiringManager;
  }

  public int getNumberOfPositions() {
    return numberOfPositions;
  }

  public void setNumberOfPositions(int numberOfPositions) {
    this.numberOfPositions = numberOfPositions;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Vacancy{"
        + "vacancyId="
        + vacancyId
        + ", vacancyName='"
        + vacancyName
        + '\''
        + ", jobTitle='"
        + jobTitle
        + '\''
        + ", description='"
        + description
        + '\''
        + ", hiringManager='"
        + hiringManager
        + '\''
        + ", numberOfPositions="
        + numberOfPositions
        + ", tenant='"
        + tenant
        + '\''
        + ", status="
        + status
        + '}';
  }
}
