package com.example.capstoneproject.main

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {

    init {
        recordsView.presenter = this
    }

    override fun start() {
        loadRecords()
    }

    override fun loadRecords() {

    }

    override fun addNewRecord() {
        recordsView.showAddRecord()
    }

    override fun openRecord() {
        TODO("Not yet implemented")
    }

    override fun deleteRecord() {
        TODO("Not yet implemented")
    }


}