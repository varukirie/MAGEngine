package indi.megaastronic.element;
/**
 * 将存活时间交给MoveHandler与ElementUtil管理
 * @author Astronic
 *
 */
public interface DurationManage {
	long getDuration();
	long getStartTime();
	void setStartTime(long startTime);
}
