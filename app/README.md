Palmare app for warehouse scanner.
This app is structured with 3 views and a final confirmation dialog.
The objective is to pickup data scanned, place them in inputs, store the data and send that data to a SAP webservice via REST API.

The first view is the type of operation the user will enable.
The second view retrieves the "commessa" code.
The third view retrieves the "materiale" and "quantita".

Each view has a back button to navigate up by one on the stack.
The third view has a "finish" button to conclude the operation.
When the button is prompted, the user receives a confirmation dialog with 2 options: back, finish.

The REST API is handled by Retrofit, gson and okhttp3 libraries.
The method is a POST call. We send a basic authorization with fixed username and password to access SAP.

For info about android apps checkout the android basic tutorial provided by google:
https://developer.android.com/courses/android-basics-kotlin/course

Explanation on the app architecture from the android basic tutorial provided above:

• RecyclerView: 
https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-recyclerview-scrollable-list#31

• Fragments and their lifecycle, and Jetpack Navigation Component:
https://developer.android.com/codelabs/basic-android-kotlin-training-fragments-navigation-component?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-fragments-navigation-component#2

• ViewModel and LiveData:
https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-3

• Activity ViewModel(shared view model):
https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#3

• REST API intro:
https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2

• Authenticate with Retrofit:
https://www.javacodemonk.com/retrofit-basic-authentication-in-android-a47245fb#:~:text=You%20will%20have%20to%20create,by%20using%20its%20basic%20function

• Inject body to authentication:
https://stackoverflow.com/questions/34791244/retrofit2-modifying-request-body-in-okhttp-interceptor

• How to create a Json Object(found in article explaining how to make retrofit api calls):
https://johncodeos.com/how-to-make-post-get-put-and-delete-requests-with-retrofit-using-kotlin/


