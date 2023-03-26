DROP TABLE IF EXISTS stampSchedule;
DROP TABLE IF EXISTS timeSchedule;
DROP TABLE IF EXISTS stamp;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	employeeID int unsigned NOT NULL AUTO_INCREMENT,
	name varchar (60) NOT NULL,
	minHours decimal (2,0),
	ptoHours decimal (5, 2),
	otEligible boolean,
	constraint employee_employeeID_pk primary key (employeeID)
	);

CREATE TABLE stamp (
	stampID int unsigned NOT NULL AUTO_INCREMENT,
	stampChoice enum ('IN', 'OUT'),
	constraint stamp_stampID_pk primary key (stampID)
	);

CREATE TABLE timeSchedule (
	scheduleID int unsigned NOT NULL AUTO_INCREMENT,
	employeeID int unsigned NOT NULL,
	clockDate DATE NOT NULL,
	clockTime TIME(0) NOT NULL,
	constraint timeSchedule_scheduleID_pk primary key (scheduleID),
	constraint timeSchedule_scheduleID_fk FOREIGN KEY (employeeID) REFERENCES employee (employeeID) ON DELETE CASCADE
	);

CREATE TABLE stampSchedule (
	scheduleID int unsigned NOT NULL,
	stampID int unsigned NOT NULL,
	constraint stampSchedule_scheduleID_fk FOREIGN KEY (scheduleID) REFERENCES timeSchedule (scheduleID) ON DELETE CASCADE,
	constraint stampSchedule_stampID_fk foreign key (stampID) REFERENCES stamp (stampID) ON DELETE CASCADE
	);
    