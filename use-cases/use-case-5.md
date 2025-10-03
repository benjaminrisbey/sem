# USE CASE: 5 Add a New Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an *HR advisor* I want *to add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope
Company.

### Level
Primary task.

### Preconditions
- A new employee has been hired.
- HR system/database is available to store employee details.

### Success End Condition
The new employee’s details are added successfully and available in the system for payroll.

### Failed End Condition
The employee’s details are not recorded, and payroll cannot process payment.

### Primary Actor
HR Advisor.

### Trigger
A new employee joins the company.

## MAIN SUCCESS SCENARIO

1. HR advisor receives new employee information (e.g., name, role, salary, bank details).
2. HR advisor opens the HR system to add employee details.
3. HR advisor enters the employee’s personal and employment details.
4. HR advisor verifies the information is correct.
5. System saves the new employee record.
6. HR advisor confirms details are available for payroll.

## EXTENSIONS

- **3a. Incomplete information provided**
    1. HR advisor requests missing details from the new employee or manager.

- **5a. System error during saving**
    1. HR advisor retries data entry or logs issue with IT support.

## SUB-VARIATIONS
None.

## SCHEDULE
**DUE DATE**: Release 1.0  
