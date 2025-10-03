# USE CASE: 6 View an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an *HR advisor* I want *to view an employee's details* so that *the employee's promotion request can be supported.*

### Scope
Company.

### Level
Primary task.

### Preconditions
- Employee details are stored in the HR system/database.
- HR advisor has access permissions to view employee information.

### Success End Condition
The HR advisor successfully views the employee’s details to support the promotion request.

### Failed End Condition
The HR advisor cannot access or find the employee’s details, delaying or preventing promotion processing.

### Primary Actor
HR Advisor.

### Trigger
An employee submits a promotion request.

## MAIN SUCCESS SCENARIO

1. HR advisor receives notification of an employee’s promotion request.
2. HR advisor opens the HR system.
3. HR advisor searches for the employee by name or ID.
4. HR advisor selects the employee from the search results.
5. HR advisor views the employee’s details (e.g., role, salary, performance history).
6. HR advisor provides relevant information to support the promotion process.

## EXTENSIONS

- **3a. Employee not found**
    1. HR advisor confirms spelling/ID.
    2. If still not found, HR advisor informs management that records are missing.

- **5a. System error or access denied**
    1. HR advisor retries or contacts IT support.

## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: Release 1.0  
