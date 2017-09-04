package indi.megaastronic.launcher;

import java.util.concurrent.ScheduledExecutorService;

public interface LauncherEvent {
	public void event(ScheduledExecutorService ses,Launcher launcher);
}
