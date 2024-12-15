package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class User
{
    private String name;
    private String user_name;
    private String mail;
    private Date birth_date;
    private String passwd;
    private boolean isPrivate;
    public int userId; //her user oluşturulduğunda bu sayı 4 haneli random bir sayı olacak
    // userin paylaştığı gönderilerin sonunda da bu sayı txtye kaydedilecek ve gönderiler çekilirken
    // bu sayıya göre çekilecek.
    // aynı zamanda arama yaparken username ile arama yapılacak ve arama kısmında username + id gözükecek.
    // arkadaş ekleme kısmında bu id girilip eklenecek ve kullanıcının profili gizliyse gözükmeyecek.
    // gruplar ise grup oluşturma kısmında grup ismi + katılımcıların idleri ile oluşturulacak.
    // gruplarım kısmında orada idsi olup ve gruba girmeye okey olan herkeste gözükecek oluşturulan grup
    // oraya yazılan idlerden herhangi birisi group-free değilse error mesajı çıkacak
    public List<Integer> friendList;
    private boolean isGroupFree;

    private String[] posts;

    @Override
    public String toString() {
        StringBuilder friendstr = new StringBuilder();
        if (friendList!=null) {
            for (int id : friendList) {
                friendstr.append(id).append("-");
            }
            friendstr.setCharAt(friendstr.length()-1,':');
        }
        if (friendstr.isEmpty()) friendstr.append(" ").append(":");
        Random random = new Random();
        userId = random.nextInt(1000);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        StringBuilder str = new StringBuilder();
        str.append(mail).append(":");
        str.append(name).append(":");
        str.append(user_name).append(":");
        str.append(sdf.format(this.birth_date)).append(":");
        str.append(isPrivate ? "private" : "public").append(":");
        str.append(isGroupFree ? "allow" : "deny").append(":");
        str.append(userId).append(":");
        str.append(friendstr);
        str.append(passwd).append("\n");
        return  str.toString();
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setGroupFree(boolean groupFree) {
        isGroupFree = groupFree;
    }
    public boolean IsGroupFree() {
        return isGroupFree;
    }
    public boolean getIsPrivate() {
        return isPrivate;
    }


    public String[] getPosts() {
        return posts;
    }

    public void setPosts(String[] posts) {
        this.posts = posts;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUserId() {
        return userId;
    }

    public void setFriendList(List<Integer> friendList) {
        this.friendList = friendList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
