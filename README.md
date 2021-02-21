# conrad-task
Technical task for https://www.conrad.de/

I think for this project I strongly need the Kafka and microservices.
SQL database must be enough for this project, I think.
SQL database provides clerks opportunities for flexible sorting and filtering data.
And I think, the next step to use Searching criteria for queries from the client.

#Preconditions
Please, set correct brokers properties for data generator and processing center.
Needed Kafka topics will be created automatically.

# Can be improved
If I need to continue developing this project, at first, I think I need to add a gateway service and authentification for users on the gateway. Use conversion service instead of separated mappers in the processor center service should be a great idea. I think that I need additional service for storing and extract citizen information and the opportunity to publish approved by the clerk incidents for delivery to the citizen.