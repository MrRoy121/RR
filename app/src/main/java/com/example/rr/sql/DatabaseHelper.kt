package com.example.rr.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.rr.model.UserModel
import java.util.*


class DatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_EMAIL + " TEXT," +
                COLUMN_USER_PASSWORD + " TEXT" + ")")

        db.execSQL(query)


        //db.execSQL(CREATE_USER_TABLE)
       // db.execSQL(CREATE_FAV_TABLE)

    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
        onCreate(db)



    }/*
    fun getAllUser(): List<UserModel> {
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_PASSWORD)
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<UserModel>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USER,columns,null,null, null,null, sortOrder)
        if (cursor.moveToFirst()) {
            do {
                val user = UserModel(id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)).toInt(),
                        name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                        email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL)),
                        password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASSWORD)))

                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }*/


    fun addUser(user: UserModel) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        db.insert(TABLE_USER, null, values)
        //db.close()
    }



/*

    fun updateUser(user: UserModel) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)

        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    fun deleteUser(user: UserModel) {
        val db = this.writableDatabase
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
                arrayOf(user.id.toString()))
        db.close()

    }
*/

    fun checkUsers(email: String): Boolean {

        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)

        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs,null, null, null)

        val cursorCount = cursor.count
        //cursor.close()
        //db.close()

        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkUser(email: String, password: String): Boolean {
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)

        val cursorCount = cursor.count
        //cursor.close()
        //db.close()

        if (cursorCount > 0)
            return true

        return false

    }

    fun getUsername(email: String): String {

        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME)
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        var ss = " "
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs,null, null, null)

        val cursorCount = cursor.count
        if (cursorCount > 0) {
            while(cursor.moveToNext()){
                ss = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME))
            }
        }
        //cursor.close()
        //db.close()

        if (cursorCount > 0) {
            return ss
        }
        return "failed"
    }

    fun getUid(email: String?): String{

        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        var ss = " "
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs,null, null, null)

        val cursorCount = cursor.count
        if (cursorCount > 0) {
            while(cursor.moveToNext()){
                ss = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
            }
        }
        //cursor.close()
        //db.close()

        if (cursorCount > 0) {
            return ss
        }
        return "failed"
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "KarthaGo"
        private val TABLE_USER = "user"
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"

    }
}
