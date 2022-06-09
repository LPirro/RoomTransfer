
# RoomTransfer
RoomTransfer is a small app made for WeTransfer that shows modern Android development: with Hilt, Coroutines, Flow, Jetpack with the new Material Design 3 guidelines.
![Logo](https://i.ibb.co/PxKNq74/room-transfer.png)
## Tech stack
- Minimum SDK 23
- 100% Kotlin
- [Material Design 3](https://m3.material.io)
- Offline Support with Room
- Dark/Light mode support
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations
- [HILT](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection
- Architecture
  - MVVM
  - Clean Architecture
  - Repository Pattern
- Jetpack
  - [Navigation](https://developer.android.com/guide/navigation): For handling Navigation inside the app
  - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle): For managing UI related data in a LifeCycle conscious way
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding): For binding UI views into controllers (like Fragments, Activities)
  - [Room](https://developer.android.com/training/data-storage/room): For creating a Database by providing an abstraction layer over SQLite
- [Retrofit / OkHttp3](https://github.com/square/retrofit): For performing network request
- [Robolectric](http://robolectric.org): For Unit Test
- [Mockito](https://site.mockito.org): Mocking framework for Unit Test
- [Turbine](https://github.com/cashapp/turbine): Turbine is a small testing library for kotlinx.coroutines Flow.
- [Glide](https://bumptech.github.io/glide/): For network image loading
- Material Components: For building the UI
- [Ktlint](https://ktlint.github.io): For code-formatting and for keeping the code style consistent across the project
## Architecture
This app is based on MVVM architecture and follows Clean Architecture principles with the repository pattern
![Architecture Diagram](https://i.ibb.co/nz3hvnY/final-002.png)
## Design
![Logo](https://i.ibb.co/6BqqNFR/figma.png)

For designing this app, I used Figma, one of the industry standard tools used by Designers for creating UI and UX for mobile and Desktop. I used the same branding colors used for the web version provided in the [following design](https://www.figma.com/file/SVCALDDXuK010oEEzo6Bn9/Book-A-Room?node-id=0%3A1) by WeTransfer. You can find the art-board of the app with all the specs here: [RoomTransfer](https://www.figma.com/file/aLMqTb4QhtgfIxk9LJEnO1/RoomTransfer)
## Things that can be improved
- **Better feedback to the user when a Room is booked:** For example, when the user books a room successfully, the button turns green with an updated label “Room Booked” and the label of the available spots updates the number as well. But I think it’s best to have this logic on the backend, so when an user books a Room, the booking endpoint will return the updated lists of rooms with the correct number of spots available and status for the user. In this way we also prevent the user to book a room multiple times, wasting room spots.
- **Using Compose for building the UI:** I've recently started learning Compose, so I don't have too much experience with it, but I think that could be really nice at some point refactor this project and migrate to Compose as it's becoming very popular and is really powerfull.