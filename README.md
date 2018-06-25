# Android Jetpack Demo

This project is a sample application to demonstrate the use of the following Jetpack libray components.

  - ViewModels
  - LiveData
  - Lifecycle
  - Databinding
  - Room
  - Paging

It uses the following architecture

![Architecture Diagram](https://codelabs.developers.google.com/codelabs/android-paging/img/511a702ae4af43cd.png)

 -  A local database that serves as a single source of truth for data presented to the UI 
 -  A web API service to refresh our database every 2 minutes.
 -  A repository that provides a unified data interface for loading from database and network
 -  A ViewModel that provides data specific for the UI
 -  The UI, which shows a visual representation of the data in the ViewModel



The code is organised in the following packages:

| Package | Description |
| ------ | ------ |
| api | contains sample API calls, using Retrofit|
| db | local database cache for network data |
| repository | contains the repository class, responsible for triggering API requests and saving the response in the database|
| ui | contains classes related to displaying a Fragment with a RecyclerView |
| model | contains the User data model, which is also a table in the Room database; and GetUsersResult, a class that is used by the UI to observe both user data and network errors|

# Final Result


![Paging View](https://i.imgur.com/sevxuc0.png)
