## Medical Referral System

Java version: 11
### Used technologies:
- Spring Framework
- Maven
- Tomcat Server 9
- JPA
- Hibernate
- MySQL
- JSP


#### Project Explanation:
System to manage medical referrals.
It can be requested the referral of a patient in a medical clinic or who was treated in an emergency at home.
There are three different roles users can have:
##### 1 - Solicitor:
Is the one who requests that a patient's referral be managed. 
##### 2 - Referral Manager:
Is the one in charge of referrals, sending requests to medical centers to accept the patient. They can manage the referrals of patients who have the coverage to which they are associated and they can make requests to the medical centers corresponding to the patient's coverage.
##### 3 - Administrative:
Is the one who receives the requests made by the referral managers, they can accept or reject the referral. They can see the requests that are made to the clinics that they have associated.

Once an administrative accept the patient and the referral manager confirms the destination, it can be seen a translation generated for the patient to the destination.
When the administrative of the medical center confirms the patient has entered the service is finished.
