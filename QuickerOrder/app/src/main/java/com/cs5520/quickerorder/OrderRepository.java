package com.cs5520.quickerorder;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class OrderRepository {
    private MutableLiveData<List<OrderDish>> searchResults = new MutableLiveData<>();
    private LiveData<List<OrderDish>> allDish;
    private OrderDao orderDao;

    public OrderRepository(Application application) {
        OrderRoomDatabase db;
        db = OrderRoomDatabase.getDatabase(application);
        orderDao = db.orderDao();
        allDish = orderDao.getAllDishes();
    }

    public void insertDish(OrderDish newdish) {
        InsertAsyncTask task = new InsertAsyncTask(orderDao);
        task.execute(newdish);
    }

    public void deleteDish(int id) {
        DeleteAsyncTask task = new DeleteAsyncTask(orderDao);
        task.execute(id);
    }

    public LiveData<List<OrderDish>> getAllDish() {
        return allDish;
    }

    public MutableLiveData<List<OrderDish>> getSearchResults() {
        return searchResults;
    }

    public void findDish(int id) {
        QueryAsyncTask task = new QueryAsyncTask(orderDao);
        task.delegate = this;
        task.execute(id);
    }


    private void asyncFinished(List<OrderDish> results) {
        searchResults.setValue(results);
    }

    private static class QueryAsyncTask extends AsyncTask<Integer, Void, List<OrderDish>> {
        private OrderDao asyncTaskDao;
        private OrderRepository delegate = null;

        QueryAsyncTask(OrderDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<OrderDish> doInBackground(final Integer... params) {
            return asyncTaskDao.findDish(params[0]);
        }

        @Override
        protected void onPostExecute(List<OrderDish> result) {
            delegate.asyncFinished(result);
        }
    }




    private static class InsertAsyncTask extends AsyncTask<OrderDish, Void, Void> {
        private OrderDao asyncTaskDao;

        InsertAsyncTask(OrderDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final OrderDish... params) {
            asyncTaskDao.insertDish(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private OrderDao asyncTaskDao;

        DeleteAsyncTask(OrderDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteDish(params[0]);
            return null;
        }
    }



}
