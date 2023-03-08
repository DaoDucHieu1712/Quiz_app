# Quizlet_clone

Project PRM392

# Đã connect firebase

FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("1").child("test");
myRef.push().setValue("Hello, 1!");

code demo add Data