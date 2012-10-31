package ub.eyewalk.states;

import ub.eyewalk.menus.IMenuCallback;
import ub.eyewalk.menus.Menu;

public class MenuListenState implements IExecutionState {
	
	private Menu menu;
	private IMenuCallback callback;
	
	public MenuListenState(Menu menu, IMenuCallback callback) { 
		this.menu = menu;
		this.callback = callback;
	}
	
	@Override
	public void shortTap() { setMenuSelection(); }

	@Override
	public void longTap() { setMenuSelection(); }
	
	private void setMenuSelection() {
		menu.stopMenu();
		callback.selectionMade(menu.getMenuChoice());
	}
	
	@Override
	public void backKeyPressed() { 
		menu.stopMenu();
		callback.selectionMade(Menu.MENU_CANCEL); 
	}

	@Override
	public void appPaused() { menu.stopMenu(); }

	@Override
	public void appResumed() { menu.resumeMenu(); }

	@Override
	public void shutdown() { menu.stopMenu(); }
	
}
