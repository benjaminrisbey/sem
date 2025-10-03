# USE CASE: 3 Produce a Report on the Salary of Employees in a department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

### Scope

Department.

### Level

Primary task.

### Preconditions

We know the department. Database contains current employee salary data.

### Success End Condition

A report is available for the department manager to use for financial reporting.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Trigger

A request for financial information is required by the department manager.

## MAIN SUCCESS SCENARIO

1. Department manager requests salary information for their department.
2. HR advisor captures the name of the department to get salary information for.
3. HR advisor extracts current salary information of all employees of the given department.
4. HR advisor provides report to finance.s

## EXTENSIONS

3. **No employee salary data exists**:
    1. HR advisor informs finance that no employee data is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0