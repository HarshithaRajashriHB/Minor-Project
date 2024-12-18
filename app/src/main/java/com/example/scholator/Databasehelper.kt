//package com.example.scholator
//
//import android.annotation.SuppressLint
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//
//class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//
//    companion object {
//        private const val DATABASE_NAME = "scholarships.db"
//        private const val DATABASE_VERSION = 1
//        private const val TABLE_NAME = "scholarships"
//        private const val COLUMN_NAME = "name"
//        private const val COLUMN_OFFERED_BY = "offered_by"
//        private const val COLUMN_AMOUNT = "amount"
//        private const val COLUMN_ELIGIBILITY = "eligibility"
//        private const val COLUMN_DESCRIPTION = "description"
//        private const val COLUMN_LINK = "link"
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "$COLUMN_NAME TEXT, " +
//                "$COLUMN_OFFERED_BY TEXT, " +
//                "$COLUMN_AMOUNT TEXT, " +
//                "$COLUMN_ELIGIBILITY TEXT, " +
//                "$COLUMN_DESCRIPTION TEXT, " +
//                "$COLUMN_LINK TEXT)"
//        db?.execSQL(CREATE_TABLE)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//        onCreate(db)
//    }
//
//    // Insert a new scholarship into the database
//    fun insertScholarship(scholarship: Scholarships) {
//        val db = this.writableDatabase
//        val contentValues = ContentValues().apply {
//            put(COLUMN_NAME, scholarship.name)
//            put(COLUMN_OFFERED_BY, scholarship.offered_by)
//            put(COLUMN_AMOUNT, scholarship.amount)
//            put(COLUMN_ELIGIBILITY, scholarship.eligibility)
//            put(COLUMN_DESCRIPTION, scholarship.description)
//            put(COLUMN_LINK, scholarship.link)
//        }
//        val result = db.insert(TABLE_NAME, null, contentValues)
//        if (result == -1L) {
//            Log.e("Database", "Failed to insert scholarship: ${scholarship.name}")
//        } else {
//            Log.d("Database", "Successfully inserted scholarship: ${scholarship.name}")
//        }
//    }
//
//    // Method to get the top scholarships
//    @SuppressLint("Range")
//    fun getTopScholarships(i: Int): List<Scholarships> {
//        val scholarships = mutableListOf<Scholarships>()
//        val db = readableDatabase
//
//        // Query to select top scholarships based on the input limit
//        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME LIMIT $i", null)
//
//        cursor?.use {
//            while (it.moveToNext()) {
//                val name = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))
//                val provider = it.getString(it.getColumnIndexOrThrow(COLUMN_OFFERED_BY))
//                val amount = it.getString(it.getColumnIndexOrThrow(COLUMN_AMOUNT))
//                val eligibility = it.getString(it.getColumnIndexOrThrow(COLUMN_ELIGIBILITY))
//                val description = it.getString(it.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
//                val link = it.getString(it.getColumnIndexOrThrow(COLUMN_LINK))
//
//                // Add each scholarship to the list
//                scholarships.add(Scholarships(name, provider, amount, eligibility, description, link))
//            }
//        }
//
//        // Return the list of scholarships
//        return scholarships
//    }
//}



package com.example.scholator

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "scholarships.db"
        private const val DATABASE_VERSION = 2  // Incremented for future schema changes
        private const val TABLE_NAME = "scholarships"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_OFFERED_BY = "offered_by"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_ELIGIBILITY = "eligibility"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_LINK = "link"
        private const val COLUMN_STATE = "state"
        private const val COLUMN_INCOME = "annual_income"
        private const val COLUMN_CASTE = "caste"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_DISABILITY = "disability"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_OFFERED_BY TEXT,
                $COLUMN_AMOUNT TEXT,
                $COLUMN_ELIGIBILITY TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_LINK TEXT,
                $COLUMN_STATE TEXT,
                $COLUMN_INCOME TEXT,
                $COLUMN_CASTE TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_DISABILITY INTEGER
            )
        """
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // In version 2, we assume columns are added in the CREATE_TABLE statement.
            // No need to ALTER the table for these columns
        }
    }

    fun insertScholarship(scholarship: Scholarships) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, scholarship.name)
            put(COLUMN_OFFERED_BY, scholarship.offered_by)
            put(COLUMN_AMOUNT, scholarship.amount)
            put(COLUMN_ELIGIBILITY, scholarship.eligibility)
            put(COLUMN_DESCRIPTION, scholarship.description)
            put(COLUMN_LINK, scholarship.link)
            put(COLUMN_STATE, scholarship.state)
            put(COLUMN_INCOME, scholarship.income)
            put(COLUMN_CASTE, scholarship.caste)
            put(COLUMN_GENDER, scholarship.gender)
            put(COLUMN_DISABILITY, if (scholarship.disability) 1 else 0)  // Store disability as INTEGER (0 or 1)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        if (result == -1L) {
            Log.e("Database", "Failed to insert scholarship: ${scholarship.name}")
        } else {
            Log.d("Database", "Successfully inserted scholarship: ${scholarship.name}")
        }
    }

    @SuppressLint("Range")
    fun getTopScholarships(i: Int): List<Scholarships> {
        val scholarships = mutableListOf<Scholarships>()
        val db = readableDatabase

        if (db == null) {
            Log.e("Database", "Database not initialized")
            return scholarships
        }

        // Query to select top scholarships based on the input limit
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME LIMIT $i", null)
        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))
                val provider = it.getString(it.getColumnIndexOrThrow(COLUMN_OFFERED_BY))
                val amount = it.getString(it.getColumnIndexOrThrow(COLUMN_AMOUNT))
                val eligibility = it.getString(it.getColumnIndexOrThrow(COLUMN_ELIGIBILITY))
                val description = it.getString(it.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val link = it.getString(it.getColumnIndexOrThrow(COLUMN_LINK))
                val state = it.getString(it.getColumnIndexOrThrow(COLUMN_STATE))
                val income = it.getString(it.getColumnIndexOrThrow(COLUMN_INCOME))
                val caste = it.getString(it.getColumnIndexOrThrow(COLUMN_CASTE))
                val gender = it.getString(it.getColumnIndexOrThrow(COLUMN_GENDER))
                val disability = it.getInt(it.getColumnIndexOrThrow(COLUMN_DISABILITY)) == 1 // Convert to Boolean

                // Add each scholarship to the list
                scholarships.add(
                    Scholarships(
                        name,
                        provider,
                        amount,
                        eligibility,
                        description,
                        link,
                        state,
                        income,
                        caste,
                        gender,
                        disability
                    )
                )
            }
        }
        return scholarships
    }

}





