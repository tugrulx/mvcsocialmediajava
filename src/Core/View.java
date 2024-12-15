package Core;

public interface View // observer interface'si ve view'i implement eden her class subscriber durumuna getirir
{
    public void update(Model model,Object data);
}
