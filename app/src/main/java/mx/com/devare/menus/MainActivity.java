package mx.com.devare.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class MainActivity extends AppCompatActivity implements View.OnLongClickListener,View.OnClickListener, ActionMode.Callback,PopupMenu.OnMenuItemClickListener{

    Button btn_menu_contextual_flotante,btn_menu_acción_contextual,btn_menu_popup;
    ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        iniciarComponentesUI();
        registroMenuContextual();
        inicializarSetOnLongClickListener();
        inicializarSetOnClickListener();
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    private void iniciarComponentesUI() {
        btn_menu_contextual_flotante=(Button) findViewById(R.id.btn_menu_contextual_flotante);
        btn_menu_acción_contextual=(Button) findViewById(R.id.btn_menu_acción_contextual);
        btn_menu_popup=(Button) findViewById(R.id.btn_menu_popup);
    }

    private void inicializarSetOnClickListener() {
        btn_menu_popup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_menu_popup:
                showPopup(v);
                break;
        }
    }

    /***********************************
     * BEGIN  MENÚ CONTEXTUAL FLOTANTE *
     ***********************************/
    private void registroMenuContextual() {
        //Se Asocia los menús contextuales a los controles
        registerForContextMenu(btn_menu_contextual_flotante);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_flotante, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_editar:
                Toast.makeText(this, "Presionastes Editar", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_eliminar:
                Toast.makeText(this, "Presionastes Eliminar", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_mostrar:
                Toast.makeText(this, "Presionastes Mostrar", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_compartir:
                Toast.makeText(this, "Presionastes Compartir", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    /********************************
     * END  MENÚ CONTEXTUAL FLOTANTE*
     ********************************/

    /****************************
     * BEGIN  MENÚ DE OPCIONES  *
     ****************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                Toast.makeText(this, "Presionastes Ajustes", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_guardar:
                Toast.makeText(this, "Presionastes guardar", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_salir:
                Toast.makeText(this, "Presionastes salir", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /****************************
     * END  MENÚ DE OPCIONES    *
     ****************************/

    /********************************
     * BEGIN  MENÚ ACCIÓN CONTEXTUAL*
     ********************************/
    private void inicializarSetOnLongClickListener() {
        // add long-click listener
        btn_menu_acción_contextual.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        // if actionmode is null "not started"
        if (mActionMode != null) {
            return false;
        }
        mActionMode = startActionMode(this);
        v.setSelected(true);
        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.setTitle("Selección");
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_accion_contextual, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_copiar:
                Toast.makeText(this, "Copiar!", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            case R.id.action_eliminar:
                Toast.makeText(this, "Eliminar!", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            case R.id.action_cortar:
                Toast.makeText(this, "Cortar!", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            case R.id.action_seleccionar_todo:
                Toast.makeText(this, "Selecionar Todo!", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
    }

    /********************************
     * END  MENÚ ACCIÓN CONTEXTUAL  *
     ********************************/

    /**********************
     * BEGIN  MENÚ POPUP  *
     **********************/
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        setForceShowIcon(popup);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_eliminar:
                Toast.makeText(getApplicationContext(), "Item Eliminar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_editar:
                Toast.makeText(getApplicationContext(), "Item Editar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_compartir:
                Toast.makeText(getApplicationContext(), "Item Compartir ", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    /**********************
     * END MENÚ POPUP     *
     **********************/

    private void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] mFields = popupMenu.getClass().getDeclaredFields();
            for (Field field : mFields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> popupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method mMethods = popupHelper.getMethod("setForceShowIcon", boolean.class);
                    mMethods.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
