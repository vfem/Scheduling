package org.vfem.schedule;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called ScheduleEntryRepository
// CRUD refers Create, Read, Update, Delete

public interface ScheduleEntryRepository extends CrudRepository<ScheduleEntry, Integer> {

}
