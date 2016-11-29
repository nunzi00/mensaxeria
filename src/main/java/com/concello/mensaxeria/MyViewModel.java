package com.concello.mensaxeria;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.Listen;

public class MyViewModel {

	private int count;

	@Init
	public void init() {
		
	}

        @Listen("onClick=button#add")
	@Command
	@NotifyChange("count")
	public void cmd() {
		++count;
	}

        
        
	public int getCount() {
		return count;
	}
}
