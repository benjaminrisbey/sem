# USE CASE: 7 Update an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an *HR advisor* I want *to update an employee's details* so that *the employee's details are kept up-to-date.*

### Scope
Company.

### Level
Primary task.

### Preconditions
- Employee details already exist in the HR system/database.
- HR advisor has access permissions to update employee information.

### Success End Condition
The employee’s details are updated successfully and reflect the most current information.

### Failed End Condition
The employee’s details are not updated, leading to inaccurate or outdated records.

### Primary Actor
HR Advisor.

### Trigger
Employee’s details change (e.g., address, role, bank details) or HR identifies incorrect information.

## MAIN SUCCESS SCENARIO

1. HR advisor identifies a need to update employee details.
2. HR advisor opens the HR system.
3. HR advisor searches for and selects the employee record.
4. HR advisor edits the relevant fields (e.g., role, salary, address, bank details).
5. HR advisor reviews the updated details for accuracy.
6. System saves the updated employee record.
7. HR advisor confirms details are now current.

## EXTENSIONS

- **3a. Employee not found**
    1. HR advisor rechecks employee information.
    2. If still missing, HR advisor informs management or IT support.

- **6a. System error during saving**
    1. HR advisor retries or raises an IT support ticket.

## SUB-VARIATIONS
- Updating personal details.
- Updating job/role details.
- Updating financial details (e.g., salary, bank information).

## SCHEDULE
**DUE DATE**: Release 1.0  
