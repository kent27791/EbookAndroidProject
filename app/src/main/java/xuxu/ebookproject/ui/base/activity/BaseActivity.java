package xuxu.ebookproject.ui.base.activity;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.model.CategoryViewModel;
import xuxu.ebookproject.model.FragmentViewModel;
import xuxu.ebookproject.model.SearchViewModel;
import xuxu.ebookproject.ui.adapter.SearchAdapter;
import xuxu.ebookproject.ui.adapter.TreeCategoryAdapter;
import xuxu.ebookproject.ui.application.EBookApplication;
import xuxu.ebookproject.ui.ebook.account.AccountActivity;
import xuxu.ebookproject.ui.ebook.book.BookActivity;
import xuxu.ebookproject.ui.session.UserSessionManager;

public abstract class BaseActivity<P extends BaseActivityPresenter> extends AppCompatActivity implements BaseActivityView {
    @BindView(R.id.home_drawer_layout)
    DrawerLayout mHomeDrawerLayout;

    @BindView(R.id.home_toolbar)
    Toolbar mHomeToolbar;

    @BindView(R.id.tree_category_recycler_view)
    RecyclerView mTreeCategoryRecyclerView;

    SearchView searchView;

    @Inject
    protected P mPresenter;

    @Inject
    protected UserSessionManager mUserSessionManager;

    protected ArrayList<FragmentViewModel> mFragmentViewModels;

    public NumberFormat mNumberFormat;

    protected ProgressDialog mProgressDialog;

    private String[] columns = new String[]{ "_id", "Value", "Display", "Type" };

    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        mFragmentViewModels = new ArrayList<>();
        setUpFragments();
        setUpComponent(getApplicationComponent());
    }



    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View baseView = layoutInflater.inflate(R.layout.activity_base, null);
        View view = layoutInflater.inflate(layoutResID, null);
        if(layoutResID != R.layout.activity_base){
            FrameLayout frameLayout = (FrameLayout) baseView.findViewById(R.id.base_content_frame_layout);
            frameLayout.addView(view);
        }
        super.setContentView(baseView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSignIn = menu.findItem(R.id.action_sign_in);
        MenuItem itemSignInSuccess = menu.findItem(R.id.action_sign_in_success);
        MenuItem itemLogOut = menu.findItem(R.id.action_logout);
        if(getUserSessionManager().isUserLoggedIn()){
            itemSignIn.setVisible(false);
            itemSignInSuccess.setTitle("Xin chào " + getUserSessionManager().getUserLoginSession().UserName).setVisible(true);
            itemLogOut.setVisible(true);
        }
        else{
            itemSignIn.setVisible(true);
            itemSignInSuccess.setVisible(false);
            itemLogOut.setVisible(false);
        }

        MenuItem itemSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchAdapter = new SearchAdapter(searchView.getContext(), R.layout.content_search_item, null, columns, null, -1000);
        searchView.setSuggestionsAdapter(searchAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2){
                    mPresenter.getDataSearch(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 2){
                    mPresenter.getDataSearch(newText);
                }
                return true;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                /*Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                String display = cursor.getString(1);
                searchView.setQuery(display, false);
                searchView.clearFocus();*/
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                /*String display = cursor.getString(2);
                searchView.setQuery(display, false);
                searchView.clearFocus();*/
                if(cursor.getInt(3) == 1){ //search book
                    Intent intent = new Intent(BaseActivity.this, BookActivity.class);
                    intent.putExtra("BookId", cursor.getInt(1));
                    BaseActivity.this.startActivity(intent);
                }else {

                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_in:{
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_sign_in_success:{
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_logout:{
                getUserSessionManager().clearUserLoginSession();
                finish();
                startActivity(getIntent());
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void setUpProviderSearch(ArrayList<SearchViewModel> data) {
        MatrixCursor cursor = new MatrixCursor(columns);
        int i = 1;
        for (SearchViewModel searchViewModel : data){
            cursor.addRow(new Object[]{ i, searchViewModel.Value, searchViewModel.Display, searchViewModel.Type });
            i++;
        }
        searchAdapter.changeCursor(cursor);

    }

    @Override
    public void setUpMenuCategory(ArrayList<CategoryViewModel> categoryViewModels) {
        mTreeCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTreeCategoryRecyclerView.setAdapter(new TreeCategoryAdapter(this, categoryViewModels));
    }

    @Override
    public void setUpActionBarDrawer() {
        setSupportActionBar(mHomeToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mHomeDrawerLayout, mHomeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mHomeDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((EBookApplication)getApplication()).getAppComponent();
    }

    public UserSessionManager getUserSessionManager(){
        return mUserSessionManager;
    }

    protected abstract int getActivityLayout();

    protected abstract void setUpComponent(ApplicationComponent applicationComponent);

    protected void setUpFragments(){

    }
}
