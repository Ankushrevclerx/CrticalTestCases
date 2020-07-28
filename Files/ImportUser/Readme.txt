Note 1:-In Import User module we are assigning Role, User group and organization to user from master sheet. so please fill master sheet (9_1_subtestcases in Data sheet) according to your requirement.
        Make sure your Role, User group and organization which you want that user user should be on the first page.(or less than equal to 30).

Note 2:- For Specific user functionality we have one column (SpecificUserFunctionality) in 9_1 testcases of master sheet, if you want it should be working during user importing process please write yes else No and also write the name of valid specific user in master sheet.

         Make sure format of imported file should be valid.

Note 1:-To Import users we have already created one CSV and TXT file which is present in your project under Files/importuser, so please fill those sheet according to your requirement.

Note 2:- During importing CSV or TXT file if status of imported file not changed from Queued or processing to Completed within 50 Second, Automation Script giving Error of Time out(In this cases please execute script again or verify file status manually ).

Note 3:- If total users imported are Zero, script will give error and 9_3 test case will not execute.

Note 4:- Script is written to handle 30 users, if users are more than 30 then you need to delete extra users.