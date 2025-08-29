db = db.getSiblingDB('reviews');

db.createUser({
  user: "user",
  pwd: "password",
  roles: [{ role: "read", db: "reviews" }]
});

// Create collections and insert data
db.games.insertMany([
  {
    "name" : "Alien Soldier",
    "platform" : "RA",
    "restricted" : false,
    "released" : "1995",
    "finished" : "2024-05-09",
    "mastered" : "2024-05-13",
    "completion" : 100.0,
    "rating" : 9,
    "notes" : "what an awesome game"
  }, {
    "name" : "Cave Story+",
    "platform" : "Steam",
    "restricted" : false,
    "released" : "2017",
    "finished" : "2020",
    "mastered" : "",
    "completion" : 88.16,
    "rating" : 9,
    "notes" : ""
  }, {
    "name" : "Curse of the Dead Gods",
    "platform" : "Steam",
    "restricted" : false,
    "released" : "2021",
    "finished" : "n/a",
    "mastered" : "2023-09-08",
    "completion" : 100.0,
    "rating" : 9,
    "notes" : ""
  }, {
    "name" : "Dark Souls: Prepare To Die Edition",
    "platform" : "Steam",
    "restricted" : false,
    "released" : "2011",
    "finished" : "2016",
    "mastered" : "2016-09-19",
    "completion" : 100.0,
    "rating" : 10,
    "notes" : "If only I could be so grossly incandescent!"
  }, {
    "name" : "Diablo 2",
    "platform" : "Win",
    "restricted" : false,
    "released" : "2000",
    "finished" : "2018",
    "mastered" : "n/a",
    "completion" : 0.0,
    "rating" : 9,
    "notes" : "difficulty: normal, nightmare, hell"
  }, {
    "name" : "DooM II: Hell on Earth",
    "platform" : "Win",
    "restricted" : false,
    "released" : "1994",
    "finished" : "2025-04-14",
    "mastered" : "n/a",
    "completion" : 0.0,
    "rating" : 10,
    "notes" : "finally finished after all these years (UV pistol start)"
  }, {
    "name" : "Gorky 17",
    "platform" : "Win",
    "restricted" : false,
    "released" : "1999",
    "finished" : "2000",
    "mastered" : "",
    "completion" : 0.0,
    "rating" : 8,
    "notes" : "Y2K gaming, oh yeah!"
  }, {
    "name" : "HROT",
    "platform" : "Steam",
    "restricted" : false,
    "released" : "2023",
    "finished" : "2023",
    "mastered" : "2023-06-02",
    "completion" : 100.0,
    "rating" : 8,
    "notes" : ""
  }, {
    "name" : "Heretic 2",
    "platform" : "Win",
    "restricted" : false,
    "released" : "1998",
    "finished" : "n/a",
    "mastered" : "",
    "completion" : 0.0,
    "rating" : -1,
    "notes" : ""
  }, {
    "name" : "Hotline Miami 2: Wrong Number",
    "platform" : "Steam",
    "restricted" : false,
    "released" : "2015",
    "finished" : "2024",
    "mastered" : "2024-02-06",
    "completion" : 100.0,
    "rating" : 8,
    "notes" : ""
  }
]);
