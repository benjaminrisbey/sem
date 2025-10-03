# USE CASE: 8 Delete an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope
Company.

### Level
Primary task.

### Preconditions
- Employee has left the company or data retention period has expired.
- HR system/database contains the employee’s details.
- HR advisor has permission to delete records.

### Success End Condition
The employee’s details are permanently deleted from the system in line with data retention legislation.

### Failed End Condition
The employee’s details are not deleted, causing non-compliance with legislation.

### Primary Actor
HR Advisor.

### Trigger
The employee leaves the company or data retention period expires.

## MAIN SUCCESS SCENARIO

1. HR advisor identifies an employee record due for deletion.
2. HR advisor opens the HR system.
3. HR advisor searches for the employee record.
4. HR advisor selects the record and chooses the delete option.
5. HR advisor confirms deletion.
6. System permanently deletes the employee’s details.
7. HR advisor verifies that the record has been removed.

## EXTENSIONS

- **3a. Employee not found**
    1. HR advisor rechecks employee details.
    2. If still not found, no deletion is required.

- **6a. System error or deletion restricted**
    1. HR advisor retries.
    2. If still unsuccessful, HR advisor raises issue with IT or compliance.

## SUB-VARIATIONS
- Deletion triggered by resignation/termination.
- Deletion triggered by legal retention period expiry.

## SCHEDULE
**DUE DATE**: Release 1.0  
