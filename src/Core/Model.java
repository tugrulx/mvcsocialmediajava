package Core;

public interface Model
{
    public void attach(View view);

    public void detach(View view);

    public void notifyViews(); // subscriberları haberdar eder
}
