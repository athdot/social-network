# CS180-PJ04
Project 4 (Social Network)

**Read This First**

This is a team project. By now, you should have completed the CATME team maker survey. If you did not complete the survey yet, you have until 11:59PM on the day the project releases, Wednesday (July 14), to do so. If you do not complete it, you will receive a 0 on the project. This information was announced on both Brightspace and Campuswire when the survey was released. 

Contact Head TA Kedar Abhyankar if you have any concerns. 

**Description**

Project 4 is the first part of a two part project. Both Project 4 and Project 5 are team projects focusing on the same topics. You'll be working on the solution with your team over the next two weeks. In Project 4, we'll establish the foundational implementation requirements and core functionality. Project 5 will expand upon that foundation with new features. Not only are these team projects, but they are also an opportunity to explore every aspect of software development independently. Think of it like the capstone for your CS 18000 experience. 

This project is worth 11% of your final grade. We recommend that you take it, along with the other projects in the class, very seriously. 

This is an open-ended project. Your team will select one of the three implementation prompts to focus on. These prompts have implementation requirements, as well as optional features. You must implement the required features. Implementing optional features may earn you bonus points, depending on their complexity and polish. Bonus points are capped at the maximum possible earned points for the project (that is, your score will not exceed 100, regardless of bonus points earned). Note: The optional features we list are only suggestions. If you have ideas about other features to implement, feel free to do so. 

Each of the implementation prompts is a limited form of a social network application. They are all similar, but the differences should allow you to specialize in what aspects of the course interest you. For Project 4, you'll be implementing an application that utilizes the keyboard (System.in) for input and the screen (System.out) for output.

Note: Several features of your implementation involve users interacting with one another. For Project 4, you can assume that users are using the same machine, logging into and out of accounts sequentially. That is, only one user accesses the application at a time. 

Lastly, this project will be manually graded after submission. We'll post scores by the Monday following the due date to ensure you have adequate time to consider feedback while working on Project 5. 

The requirements for each option are grouped into two categories: Core and Selections. Each team must implement the core requirements, and select the appropriate number of selective requirements based on the number of students in their team. Teams with 5 members should select 3 selective requirements, 4 member teams should select 2, and 3 member teams should select 1. Leftover requirements will count towards the "optional" requirements for extra credit. 

Note: 5 points of your grade is based on Coding Style.  You will need to update the Starter Code to follow the standards described here  Use the "Run" button to check your Coding Style without using a submission. 

**Team Work Expectations**

There will be 3, 4, or 5 team members on each team. You will be placed into a chat group on Campuswire with your team. We expect each member of every team to contribute to the project. You are permitted to divide the work in any way you see fit as long as responsibilities are evenly distributed and every team member contributes to the project source code. You will be required to document your contributions to the project in the planning document. 

Each team member must send a message to your Campuswire chat group by the Friday (July 16) after the project releases at 11:59PM EDT. Failure to do so will result in a 0 on the project for that team member. There are no exceptions to this policy. 

We recommend using the Campuswire chat functionality to communicate with your team. Using this feature will make it easier for course staff to support your team (you can ask us to answer questions via the @ function) and helps us mediate any potential disputes. Regardless of your choice of communication tool, you must send a message to the Campuswire chat by the Friday (July 16) after the project releases at 11:59PM EDT to be graded. 

Any team member who fails to contribute will receive a 0 on the project.

Be aware that team collaboration is limited to the members of your team. You should not be sharing code with individuals outside of your team. Remember to follow the course Academic Honesty policy. If you have concerns about whether or not something is okay, just ask us. 

To simplify collaboration, you must make use of a Code Repository on Gitlab or Github. It will make sharing code, tracking changes, and debugging significantly easier. However, keep in mind that any repository you use must be private, with access limited only to members of your team. Code made available publicly is academic dishonesty. You will be required to submit a copy of your repository on Vocareum by cloning it into your work folder. 

Note: Every team member must commit to the repository. A lack of commits may be used as evidence that a team member did not participate. 

We reserve the right to modify your grade based on participation. You may receive a 0 for not contributing at all, or you may receive a 75% deduction on your team score for only contributing superficial content. If you wait until the last minute to work on the assignment, you will receive a 0 or a significant deduction as well. These cases will be judged on a case-by-case basis using evidence provided by teams. 

Your team will share a workspace on Vocareum. Only one team member needs to submit. 

In the event of a team disagreement, dispute, or lack of participation from any individual, you should contact the course coordinators as soon as possible. We can only help if we are aware of the situation. 

**Role Distribution**

Every team member needs to work on this project. Before you begin coding, we highly recommend that you meet and identify roles for everyone. For example: 

Role 1: Develop menus and application control flow.
Role 2: Develop log in details and user permissions. 
Role 3: Develop option specific requirements such as the profile or messaging functionality. 
Role 4: Develop option specific requirements such as the profile or messaging functionality. 
Role 5: Develop data persistence solutions and test cases. 
You can define the roles in any way that your team prefers. Two team members may work on the same topic, as we note above. Alternatively, you may find it simpler to have everyone complete a specific task separately. Communication will be key! 

Remember, this is a team project. Your project score will reflect your contribution to the team. 

**Implementation Restrictions**

Before describing each of the options, we want to note several key restrictions on your project implementations. 

First, all Project 4 input and output should be handled via the keyboard (System.in) and screen (System.out), just as with all of your previous projects. There is no GUI for this project. 

Second, all data must persist between program runs. If a user creates an account and then closes the application, they should still be able to log in when running it again. 

Third, all work must be your own. Copying code from the internet is academic dishonesty. Any team member who is caught copying code will receive a 0 on the project and the other team members will be given an opportunity to continue without them. 

Keep these in mind while designing your solution! 

**Option 1**

The first option is to implement a social network "posting" application. Most social networks allow users to make text based posts to share their thoughts or ideas. This will be a similar concept.

Reminder: You can assume that only one user is accessing the application at a time. A user might log in, make a post, then log out. Another user on the same machine can then log in, view the original user's post, and make one of their own. 

**Your implementation must have the following: **

**Core**

Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present. 
Descriptive errors should appear as appropriate. For example, if someone tries to log in with an invalid account. The application should not crash under any circumstances. 
Users can create, edit, and delete accounts for themselves.
The attributes you collect as part of account creation are up to you. 
Users should be required to either create an account or sign in before gaining access to the application. 
Whichever identifier you maintain for the user must be unique. 
Users can create, edit, and delete posts.
Posts must include a title, author name, text content, and a timestamp. 
Users must have the option to list all posts. 
Posts should be listed with the newest first. 
Remember to restrict edit and delete access to the user who created the post. 

**Selections**

Users can import or export posts using a csv file.
All post details should be preserved: title, author name, text content, and timestamp. 
When importing, update the timestamp to the current time. 
Users can create, edit, and delete comments on specific posts.
All comments on a post should include the author name, comment text, and a timestamp.  
Remember to restrict edit and delete access to the user who created the comment. 
Users can view all of the posts and comments made by a specific user by searching their name or another identifier. 

**Optional Features:** 

Add emoji responses to posts and list how many of the responses have been selected in the post. 
Add polling features to posts. 
Allow certain users to moderate posts by editing or removing them. 

**Option 2**

The second option is to implement a social network "profile" application. Most social networks allow users to make profiles that document their personal information, interests, and other relevant characteristics. This will be a similar concept.

Reminder: You can assume that only one user is accessing the application at a time. A user might log in, make a profile, then log out. Another user on the same machine can then log in, view the original user's profile, and make one of their own. 

**Your implementation must have the following:** 

**Core**

Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present. 
Descriptive errors should appear as appropriate. For example, if someone tries to log in with an invalid account. The application should not crash under any circumstances. 
Users can create, edit, and delete accounts for themselves.
The attributes you collect as part of account creation are up to you. 
Users should be required to either create an account or sign in before gaining access to the application. 
Whichever identifier you maintain for the user must be unique. 
Users can create, edit, and delete profiles.
Remember to restrict edit and delete access to the user who created the profiles.
Users must have the option to list all profiles. 
Profiles should be listed with the newest first. 
 A profile should contain a minimum of name, contact information, likes / interests, a friends list, and "about me". 
Users can create a friends list of other users of the application. 
To be friends, both users must have an account. One user must send a friend request to another by entering their name or another identifier. No confirmation is required.

**Selections**

Users can import or export profiles using a csv file.
All profile details should be preserved: contact information, likes / interests, a friends list, and "about me" (other details if necessary). 
Update the friend request functionality to prevent automatic friending. Users can see a list of their sent and received friend requests. They can deny friend requests or confirm them. 
If a friend request is denied, it will no longer appear for the recipient or the sender. 
If a friend request is confirmed, it will no longer appear for the recipient or the sender. Both users will be added to each other's friend list. 
Users can view a list of all the application's users and send any given individual a friend request or view their profile. 
Optional Features: 
Users can customize their feed to only list the profiles of friends, friends of friends, or everyone with specific accounts excluded. 
Users can create and manage multiple profiles for themselves or others. 
Allow accounts to be set to "private", where no other user can view them, and "protected", where only current friends can view them.

**Option 3**

The third option is to implement a social network "messaging" application. Most social networks allow users to chat with one another in group and private settings. This will be a similar concept.

Reminder: You can assume that only one user is accessing the application at a time. A user might log in, send a message, then log out. Another user on the same machine can then log in, view the original user's message, and send a reply. 

**Your implementation must have the following:**

**Core**

Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present. 
Descriptive errors should appear as appropriate. For example, if someone tries to log in with an invalid account.The application should not crash under any circumstances. 
Users can create, edit, and delete accounts for themselves.
The attributes you collect as part of account creation are up to you. 
Users should be required to either create an account or sign in before gaining access to the application. 
Whichever identifier you maintain for the user must be unique. 
Users can create, edit, and delete individual messaging conversations and group messaging conversations with the users of their choice.
Remember that all users must have accounts. 
Remember to restrict edit and delete access to the user who created the messages .
Remember to restrict access to those conversations to the message participants. 

**Selections**

Users can import or export details for one or more of their conversations using a csv file.
All message details should be preserved: Participants, Message sender, timestamp, and contents.
Users can see a list of all their conversations in one place. They can select a particular conversation to enter it and return to the list once they are finished. 
Users can delete a conversation from their list. 
This should not delete the conversation from the other participant's lists. 

**Optional Features: **

Notify users of new messages when they log in. 
Allow users to customize message environments with features such as: creating group names, setting group specific nicknames, and so on. A minimum of three customization options is required. 
Add a moderator role to kick or ban members from a group message, or have additional powers different from the standard user.

**Testing**

There are no public test cases for this project. Each implementation will look different, and we do not want to restrict your creativity in any way.

However, you are expected to write your own custom test cases, specific to your team's implementation. We've given you examples of what this looks like in all your previous assignments.  Here's an example from one of the Homework assignments: 

@MethodTesting(timeout = 1000)
public void testExpectedOne() {
    // Set the input        
    // Separate each input with a newline (\n). 
    String input = "Line One\nLine Two\n"; 

    // Pair the input with the expected result
    String expected = "Insert the expected output here" 

    // Runs the program with the input values
    // Replace TestProgram with the name of the class with the main method
    receiveInput(input);
    TestProgram.main(new String[0]);

    // Retrieves the output from the program
    String stuOut = getOutput();

    // Trims the output and verifies it is correct. 
    stuOut = stuOut.replace("\r\n", "\n");
    assertEquals("Error message if output is incorrect, customize as needed",
                    expected.trim(), stuOut.trim());

}

**Your test cases should do the following: **

Each requirement must have a test verifying it functions properly.
Each requirement should have an error test to verify it does not crash when receiving invalid input. 
Hint: For the requirements that verify data persists between runs, you can check the file contents in the test case. 
When designing your implementation, be sure to use methods appropriately. It will be challenging to design test cases for overly complex methods.  

**Project 5 Preview**

Project 5 is a new iteration of Project 4. You'll be improving your solution with new features and making it more accessible to users. 

There are three core improvements included in Project 5: 

**Concurrency** - Your program will support concurrent use by multiple users.

**Network IO** - Your program will support networked use with a server and client. 

**GUI** - Your program will have a fully-featured GUI that allows users to perform all the tasks they could do in the command line. 
You don't have to worry about any of these yet, but you should keep them in mind while working on Project 4. The design decisions you make now will affect how easy it is to implement these features later!

**Review and Planning Report**

Your team must submit a project review and planning document for Project 4. In it, you'll consider lessons learned from Project 4 and document how you will approach Project 5. 

**Part One**

Part One is a minimum of 500 words and requires the following: 

Describing your project and the functionality you implemented. Include both required and optional features, along with descriptions of each.

**Part Two**

Each team member must write a minimum of 300 words on the following:

A minimum of 200 words describing your contributions to Project 4. 
A minimum of 100 words on what you would do differently, if anything, if you were given the opportunity to start over again. If you would do something differently, note how you will apply that knowledge in Project 5. If you would not do anything differently, explain why. 
Each team member's section should be labelled with that individual's name. 
Part Three
Part Three is a minimum of 400 words and requires the following: 

A minimum of 200 words describing your collaboration strategy for Project 5, including how work will be assigned and how team members will communicate. 
A minimum of 100 words containing a list of tasks you believe will be necessary to complete Project 5, along with the individuals assigned to complete them and when they should be done. You may use a list or paragraph for this section. 
 For example, "Develop Server class" - Completed by 07/26/2021 - Student One. 
Note: There must be an even distribution of work among the team members.
A minimum of 100 words describing your conflict mitigation strategy. What will you do to prevent team conflict? How will you handle disagreements if they do arise? 
Note: Be sure your document does not have any major grammar or structural errors. You are not required to adhere to any writing style guide (for example, APA), but your document should be organized following the guidelines listed above.  You can use the following structure for a general outline: 

A cover page with the report title and all team member names listed
A section labelled "Part 1" on a new page with the information described above (including as many pages as necessary). 
A section labelled "Part 2" on a new page with the information described above (including as many pages as necessary). 
A section labelled "Part 3" on a new page with the information described above (including as many pages as necessary). 
We recommend using a 12 point font such as Times New Roman and double spacing. All section and subsection labels should be bolded.

**Documentation**

Your project must follow Coding Style (it will be 5 points of your solution grade). You should also document your code with comments explaining the functionality you implement. Not only will your teammates appreciate it if they have to debug, but you will also make it simpler to explain why you chose to implement it that way if asked during the presentation. 

Additionally, your project will need to have a README document. This document will include the following:

Instructions on how to compile and run your project.
A list of who submitted which parts of the assignment on Brightspace and Vocareum. 
For example: Student 1 - Submitted Report on Brightspace. Student 2 - Submitted Vocareum workspace.
A detailed description of each class. This should include the functionality included in the class, the testing done to verify it works properly, and its relationship to other classes in the project. 

**Submit**

Here's a breakdown of grading: 

All solution code, test cases, and documentation must be submitted on Vocareum by cloning the repository. (80 points). All required files should be included in the repository. 
A written report must be submitted via Brightspace. (10 points).
The peer evaluation form must be submitted via CATME. (5 points).
Coding Style (5 points)
Project 5 is a new iteration of Project 4. You'll be improving your solution with new features and making it more accessible to users. 

There are three core improvements included in Project 5: 

Concurrency - Your program will support concurrent use by multiple users. 
Network IO - Your program will support networked use with a server and client. 
GUI - Your program will have a fully-featured GUI that allows users to perform all the tasks they could do in the command line. 
You don't have to worry about any of these yet, but you should keep them in mind while working on Project 4. The design decisions you make now will affect how easy it is to implement these features later!

**Review and Planning Report**

Your team must submit a project review and planning document for Project 4. In it, you'll consider lessons learned from Project 4 and document how you will approach Project 5. 

**Part One**

Part One is a minimum of 500 words and requires the following: 

Describing your project and the functionality you implemented. Include both required and optional features, along with descriptions of each. 

**Part Two**

Each team member must write a minimum of 300 words on the following:

A minimum of 200 words describing your contributions to Project 4. 
A minimum of 100 words on what you would do differently, if anything, if you were given the opportunity to start over again. If you would do something differently, note how you will apply that knowledge in Project 5. If you would not do anything differently, explain why. 
Each team member's section should be labelled with that individual's name.

**Part Three**

Part Three is a minimum of 400 words and requires the following: 

A minimum of 200 words describing your collaboration strategy for Project 5, including how work will be assigned and how team members will communicate. 
A minimum of 100 words containing a list of tasks you believe will be necessary to complete Project 5, along with the individuals assigned to complete them and when they should be done. You may use a list or paragraph for this section. 
 For example, "Develop Server class" - Completed by 07/26/2021 - Student One. 
Note: There must be an even distribution of work among the team members.
A minimum of 100 words describing your conflict mitigation strategy. What will you do to prevent team conflict? How will you handle disagreements if they do arise? 
Note: Be sure your document does not have any major grammar or structural errors. You are not required to adhere to any writing style guide (for example, APA), but your document should be organized following the guidelines listed above.  You can use the following structure for a general outline: 

A cover page with the report title and all team member names listed
A section labelled "Part 1" on a new page with the information described above (including as many pages as necessary). 
A section labelled "Part 2" on a new page with the information described above (including as many pages as necessary). 
A section labelled "Part 3" on a new page with the information described above (including as many pages as necessary). 
We recommend using a 12 point font such as Times New Roman and double spacing. All section and subsection labels should be bolded. 

**Documentation**

Your project must follow Coding Style (it will be 5 points of your solution grade). You should also document your code with comments explaining the functionality you implement. Not only will your teammates appreciate it if they have to debug, but you will also make it simpler to explain why you chose to implement it that way if asked during the presentation. 

Additionally, your project will need to have a README document. This document will include the following:

Instructions on how to compile and run your project.
A list of who submitted which parts of the assignment on Brightspace and Vocareum. 
For example: Student 1 - Submitted Report on Brightspace. Student 2 - Submitted Vocareum workspace.
A detailed description of each class. This should include the functionality included in the class, the testing done to verify it works properly, and its relationship to other classes in the project.

**Submit**

Here's a breakdown of grading: 

All solution code, test cases, and documentation must be submitted on Vocareum by cloning the repository. (80 points). All required files should be included in the repository. 
A written report must be submitted via Brightspace. (10 points).
The peer evaluation form must be submitted via CATME. (5 points).
Coding Style (5 points)
