package Models;

import Core.Model;
import Core.View;

import java.io.*;
import java.util.*;

public class UserIO implements Model
{

    // field 0 mail
    // field 1 name
    // field 2 username
    // field 3 birthdate
    // field 4 isPrivate
    // field 5 is group allowed
    // field 6 user id
    // field 7 friend list
    // field 8 password
    public static final int MAIL_FIELD = 0;
    public static final int NAME_FIELD = 1;
    public static final int USERNAME_FIELD = 2;
    public static final int BIRTHDATE_FIELD = 3;
    public static final int PRIVATE_FIELD = 4;
    public static final int GROUP_FIELD = 5;
    public static final int ID_FIELD = 6;
    public static final int FRIENDS_FIELD = 7;
    public static final int PASSWORD_FIELD = 8;
    public static final int GROUP_MEMBERS_FEILD= 2;
    public static final int GROUP_NAME_FIELD=0;
    public static final int GROUP_DESC_FIELD=1;

    private static final String DIRECTORY = "."; // current directory
    private static final String USER_LIST = "users.txt";
    private static final String LOG_LIST = "log.txt";
    private static final String POST_LIST = "posts.txt";
    private static final String GROUP_LIST = "groups.txt";

    private List<View> views =new ArrayList<>(); // yüm viewler burada tutuluyor
    private String notice;


    @Override
    public void attach(View view)
    {
        views.add(view);
    }

    @Override
    public void detach(View view) {
        views.remove(view);
    }

    @Override
    public void notifyViews() {
        for (View v : views) {
            v.update(this,notice);
        }
    }

    public int SaveUser(User user) // users.txt'ye kullanıcıyı kaydeder, bunu yaparken users.toStringe göre yapar
    {
        notifyViews();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,USER_LIST),true));
            writer.write(user.toString(),0,user.toString().length());
            writer.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
            return 0;
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
            return 0;
        }
        Log(user);
        return 1;

    }

    public void Log(User user)  // en başarılı giriş yapmış kullanıcıyı log.txt'ye yazar
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,LOG_LIST),true));
            writer.write(user.getMail());
            writer.write(":");
            writer.write(user.getPasswd());
            writer.write(":");
            writer.write(user.getUserId());
            writer.write("\n");
            writer.close();
        }
        catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
        }
    }
    public void Log(String mail,String passwd,String id) // en son başarılı giriş yapmış kullanıcıyı log.txtye yazar
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,LOG_LIST),true));
            writer.write(mail);
            writer.write(":");
            writer.write(passwd);
            writer.write(":");
            writer.write(id);
            writer.write("\n");
            writer.close();
        }
        catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
        }
    }

    public  Vector<Vector<Object>> GetUsers() // bütün kullanıcıları tabloya eklemelik biçimde döndürür.
    {
        Vector<Vector<Object>> response  = new Vector<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] fields = line.split(":");
                Vector<Object> single_user = new Vector<>(Arrays.asList(fields));
                /*
                for (String field : fields) { // bu şekilde de yapılabilir
                    single_user.add(field);
                }
                */
                response.add(single_user);
                line=reader.readLine();

            }
            reader.close();
        }
        catch (Exception e ) {
            notice = e.toString();
            notifyViews();
        }

        return response;
    }


    public int SignIn(String mail,String passwd) // mail şifre kombinasyonu uyuyorsa 1 döndürerek
    { // isLogged'in true olmasını sağlar ve başarılı girişi loglar.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(":");
                if (fields[MAIL_FIELD].equals(mail) && fields[PASSWORD_FIELD].equals(passwd))
                {
                    Log(mail,passwd,fields[ID_FIELD]);
                    return 1;
                }
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
            return 0;
        }
        return 0;
    }

    public Vector<Vector<Object>> getLastLogged() // son giriş yapmış olan kişiyi users.txt'de bulup tabloya
    {           // eklemeye uygun olarak döndürüyor, profile view için gerekli
        Vector<Vector<Object>> lastLogged = new Vector<>();
        UserUtil util =new UserUtil();
        Vector<Object> userfields = new Vector<>();
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(new File(DIRECTORY,LOG_LIST)));
            String line = reader1.readLine();
            String lastLine = null;
            while (line != null) {
                lastLine = line;
                line= reader1.readLine();
            }
            String[] mail_passwd = lastLine.split(":");
            BufferedReader reader2=new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String currentUser =  reader2.readLine();
            String[] currentFields = null;
            while (currentUser != null) {
                currentFields = currentUser.split(":");
                if (currentFields[MAIL_FIELD].equals(mail_passwd[0]) && currentFields[PASSWORD_FIELD].equals(mail_passwd[1])) break;
                currentUser=reader2.readLine();
            }
            if (currentUser!=null) {
                userfields.add(currentFields[MAIL_FIELD]);
                userfields.add(currentFields[NAME_FIELD]);
                userfields.add(currentFields[USERNAME_FIELD]);
                userfields.add(util.getDateFromAString(currentFields[BIRTHDATE_FIELD]));
                userfields.add(currentFields[PRIVATE_FIELD]);
                userfields.add(currentFields[GROUP_FIELD]);
                userfields.add(currentFields[ID_FIELD]);
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        lastLogged.add(userfields);
        return lastLogged;
    }
    public Vector<Vector<Object>> getFriendsOfUser() // kullanıcının arkadaş listesini döndürüyor, tabloya ekleme
    {       // biçiminde
        String id = FindLastLogged().split(":")[ID_FIELD];
        UserUtil util  =new UserUtil();
        Vector<Vector<Object>> all_friendList = new Vector<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] fields = line.split(":");
                List<String> friendList = new ArrayList<>();
                String[] friendStr = fields[FRIENDS_FIELD].split("-");
                for (String friend : friendStr) {
                    friendList.add(friend);
                }
                if (friendList.contains(id))
                {
                    Vector<Object> aFriend = new Vector<>();
                    aFriend.add(fields[MAIL_FIELD]);
                    aFriend.add(fields[NAME_FIELD]);
                    aFriend.add(fields[USERNAME_FIELD]);
                    aFriend.add(util.getDateFromAString(fields[BIRTHDATE_FIELD]));
                    aFriend.add(fields[PRIVATE_FIELD]);
                    aFriend.add(fields[GROUP_FIELD]);
                    aFriend.add(fields[ID_FIELD]);
                    all_friendList.add(aFriend);
                }
                line=reader.readLine();
            }
        }
        catch (Exception e)
        {
            notice = e.toString();
            notifyViews();
        }
        return all_friendList;
    }

    private String FindLastLogged()  // en son giriş yapmış kişiniin tüm datasını splitlenmemiş string halinde döndürüyor.
    {
        String currentUser= null;
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(new File(DIRECTORY,LOG_LIST)));
            String line = reader1.readLine();
            String lastLine = null;
            while (line != null) {
                lastLine = line;
                line= reader1.readLine();
            }
            String[] mail_passwd = lastLine.split(":");
            BufferedReader reader2=new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            currentUser =  reader2.readLine();
            String[] currentFields;
            while (currentUser != null) {
                currentFields = currentUser.split(":");
                if (currentFields[MAIL_FIELD].equals(mail_passwd[0]) && currentFields[PASSWORD_FIELD].equals(mail_passwd[1])) break;
                currentUser=reader2.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return currentUser;
    }

    public Vector<Object> GetUserFromId(String id) // idsi verilmiş olan kişinin datası döndürülüyor.
    {
        UserUtil util  =new UserUtil();
        Vector<Object> user = new Vector<>();
        boolean found = false;
        String line=null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            line = reader.readLine();
            while (line != null)
            {
                String[] fields = line.split(":");
                if (id.equals(fields[ID_FIELD])) {
                    found=true;
                    break;
                }
                line = reader.readLine();
            }
        }
        catch (Exception e )
        {
            notice = e.toString();
            notifyViews();
        }
        String[] fields = line.split(":");
        if (found)
        {
            user.add(fields[MAIL_FIELD]);
            user.add(fields[NAME_FIELD]);
            user.add(fields[USERNAME_FIELD]);
            user.add(util.getDateFromAString(fields[BIRTHDATE_FIELD]));
            user.add(fields[PRIVATE_FIELD]);
            user.add(fields[GROUP_FIELD]);
            user.add(fields[ID_FIELD]);
        }
        if (!found) {
            return null;
        }
        return user;
    }

    public Vector<Vector<Object>> GetPostsOfLastLogged() // en son giriş yapmış kişinin paylaşmıl olduğu gönderiler
    {       // tabloya ekleme biçiminde döndürülüyor.
        Vector<Vector<Object>> posts = new Vector<>();
        String lastLog = FindLastLogged();
        String[] fields = lastLog.split(":");
        String id = fields[ID_FIELD];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,POST_LIST)));
            String postLine = reader.readLine();

            while (postLine!=null) {
                String[] parts = postLine.split("~");
                if (parts[1].equals(id))
                {
                    Vector<Object> post = new Vector<>();
                    post.add(parts[0]);
                    posts.add(post);
                }
                postLine= reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        if (posts.isEmpty()) {
            return null;
        }
        return posts;
    }

    public Vector<Object> SavePost(String post) // post paylaşma işlemi yapılmışsa en son giriş yapan kişi log.txt
    { // den alındıktan sonra idsi bulunup post~id şeklinde posts.txt'ye kaydediliyor.
        Vector<Object> obj = new Vector<>();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,POST_LIST),true));
            String lastLogged = FindLastLogged();
            String[] fields = lastLogged.split(":");
            String id = fields[ID_FIELD];
            writer.write(post);
            writer.write("~");
            writer.write(id);
            writer.write("\n");
            writer.close();
            obj.add(post);
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return obj;
    }

    public void UpdateFriends(String newFriendID)
    {
        // arkadaş listesinde newFriendID satırında arkadaş listesine id'yi
        // id satırında da arkadaş listesine newFriendID ekleyecek.
        // yani iki kullanıcının da arkadaş listesini users.txt'de güncelliyor, txt'nin tamamını ele alıp txtyi silip
        // ilgili kullannıcıların satırlarını güncelleyip, diğerlerini ise güncellemeden tekrar yazıyor
        String id = FindLastLogged().split(":")[ID_FIELD];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            StringBuilder wholeText = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                wholeText.append(line).append('\n');
                line =reader.readLine();
            }
            wholeText.deleteCharAt(wholeText.length()-1);
            String[] allUsers = wholeText.toString().split("\n");
            for (int x=0; x<allUsers.length; x++) {
                String currentUser = allUsers[x];
                String[] currentFields = currentUser.split(":");
                if (currentFields[ID_FIELD].equals(id)) // kullanıcının friend listesini güncelle
                {
                    StringBuilder str = new StringBuilder();
                    for (String friend : currentFields[FRIENDS_FIELD].split("-"))
                    {
                        if (friend.equals(" ")) continue;
                        str.append(friend).append("-");
                    }
                    str.append(newFriendID);
                    currentFields[FRIENDS_FIELD]=str.toString();
                    StringBuilder str2 = new StringBuilder();
                    for (String fields : currentFields)
                    {
                        str2.append(fields).append(":");
                    }
                    str2.deleteCharAt(str2.length()-1);
                    allUsers[x]=str2.toString();
                }
                if (currentFields[ID_FIELD].equals(newFriendID)) // eklenen yeni kullanıcının ark listsini güncelle
                {
                    StringBuilder str = new StringBuilder();
                    for (String friend : currentFields[FRIENDS_FIELD].split("-"))
                    {
                        if (friend.equals(" ")) continue;
                        str.append(friend).append("-");
                    }
                    str.append(id);
                    currentFields[FRIENDS_FIELD]=str.toString();
                    StringBuilder str3 = new StringBuilder();
                    for (String fields : currentFields)
                    {
                        str3.append(fields).append(":");
                    }
                    str3.deleteCharAt(str3.length()-1);
                    allUsers[x]=str3.toString();
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,USER_LIST),false));
            for (int x=0; x<allUsers.length; x++) {
                writer.write(allUsers[x]);
                writer.write("\n");
            }
            writer.close();
        }
        catch (Exception e)
        {
            notice = e.toString();
            notifyViews();
        }

    }

    public Vector<Vector<Object>> GetGroupList()  // en son giriş yapmış kullanıcının katılmış olduğu grupları
    {   // döndürür, tablo ekleme formatında.
        String id = FindLastLogged().split(":")[ID_FIELD];
        Vector<Vector<Object>> groupList = new Vector<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,GROUP_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] fields = line.split(":");
                String[] memberIDs = fields[GROUP_MEMBERS_FEILD].split("-");
                for (String memberID : memberIDs) {
                    if (memberID.equals(id)) {
                        Vector<Object> group = new Vector<>();
                        group.add(fields[GROUP_NAME_FIELD]);
                        group.add(fields[GROUP_DESC_FIELD]);
                        StringBuilder str = new StringBuilder();
                        for (String s : memberIDs) {
                            str.append(GetNameFromID(s)).append("   ");
                        }
                        group.add(str.toString());
                        groupList.add(group);
                    }
                }
                line =reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return groupList;
    }
    public String GetNameFromID(String id)  // id'den user bulunup kullanıcı adı döndürülüyor
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line !=null) {
                if (line.split(":")[ID_FIELD].equals(id)) return line.split(":")[NAME_FIELD];
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return null;
    }
    public List<String> GetFriendListAsIDs() // en son login olmuş kişinin arkadaş listesini döndürür, liste isimlerden oluşur.
    {
        String id = FindLastLogged().split(":")[ID_FIELD];
        List<String> friends = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] friendsOfCurrent = line.split(":")[FRIENDS_FIELD].split("-");
                for (String str : friendsOfCurrent) {
                    if (str.equals(id)) {
                        friends.add(str);
                    }
                }
                line= reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return friends;
    }
    public List<String> GetFriendListAsNames() // en son login olmuş kişinin arkadaş listesini döndürür, liste isimlerden oluşur.
    {
        String id = FindLastLogged().split(":")[ID_FIELD];
        List<String> friends = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] friendOfCurrent= line.split(":")[FRIENDS_FIELD].split("-");
                for (String str : friendOfCurrent) {
                    if (str.equals(id)) {
                        friends.add(GetNameFromID(str));
                    }
                }
                line= reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return friends;
    }
    public List<String> GetGroupAllowedFriendsOfUserAsNames() // en son login olmuş kişinin arkadaş listesini döndürür, liste idlerden oluşur.
    {   // ama group ekleme allow olanları
        String id = FindLastLogged().split(":")[ID_FIELD];
        List<String> friends = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] friendsOfCurrent = line.split(":")[FRIENDS_FIELD].split("-");
                for(String IDs : friendsOfCurrent)
                {
                    if (IDs.equals(id) && line.split(":")[GROUP_FIELD].equals("allow"))
                    {
                        if (!friends.contains(GetNameFromID(line.split(":")[ID_FIELD]))) {
                            friends.add(GetNameFromID(line.split(":")[ID_FIELD]));

                        }
                    }
                }
                line= reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return friends;
    }
    public List<String> GetGroupAllowedFriendsOfUserAsIDs() // en son login olmuş kişinin arkadaş listesini döndürür, liste idlerden oluşur.
    {
        String id = FindLastLogged().split(":")[ID_FIELD];
        List<String> friends = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null)
            {
                String[] friendsOfCurrent = line.split(":")[FRIENDS_FIELD].split("-");
                for(String IDs : friendsOfCurrent)
                {
                    if (IDs.equals(id) && line.split(":")[GROUP_FIELD].equals("allow"))
                    {
                        friends.add(line.split(":")[ID_FIELD]);
                    }
                }
                line= reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return friends;
    }

    public String GetUser(String id) {// idsi girilmiş kullanıcının bilgilerini döndürür.
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null) {
                if (line.split(":")[ID_FIELD].equals(id)) return line;
                line=reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return null;
    }

    public Vector<Object> SaveGroup(List<String> memberIDs, String name, String desc)
    {
        Vector<Object> newRecord = new Vector<>();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY,GROUP_LIST),true));
            writer.write(name);
            writer.write(":");
            writer.write(desc);
            writer.write(":");
            StringBuilder str  =new StringBuilder();
            for (String id : memberIDs) {
                str.append(id).append("-");
            }
            str.append(FindLastLogged().split(":")[ID_FIELD]);
            newRecord.add(name);
            newRecord.add(desc);
            newRecord.add(str.toString());
            writer.write(str.toString());
            writer.write("\n");
            writer.close();
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return newRecord;
    }

    public ArrayList<String> GetUserAndUsernames()
    {
        ArrayList<String> results = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY,USER_LIST)));
            String line = reader.readLine();
            while (line != null) {
                StringBuilder str = new StringBuilder();
                String[] fields = line.split(":");
                str.append(fields[USERNAME_FIELD]).
                        append(",").
                        append(fields[NAME_FIELD]).
                        append(",").
                        append(fields[PRIVATE_FIELD]);
                results.add(str.toString());
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            notice = e.toString();
            notifyViews();
        }
        return results;
    }
}
