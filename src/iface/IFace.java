
package iface;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class IFace {
    
    public static Scanner read = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Community> communities = new ArrayList<Community>();
    
    /*main START*/
    public static void main(String[] args) {
        firstPage();
    }
    /*main FINISH*/
    
    /*firstPage START*/
    public static void firstPage(){
        int iaux;
        String saux;
        System.out.println("[!] Bem-vindo ao iFace!");
        System.out.println("[+] Digite 1 para FAZER LOGIN, 2 para se CADASTRAR ou 0 para SAIR: ");
        iaux = readNumber();
        saux = read.nextLine();
        
        if(iaux == 1){
            login();
        }else if(iaux == 2){
            register();
            login();
        }else{
            System.out.println("[!] Volte sempre!");
        }
    }
    /*firstPage FINISH*/
    
    /*login START*/
    public static void login(){
        String login;
        String password;
        User actUser;
        int iaux; //Auxiliar para inteiros
        
        System.out.println("[+] Digite o seu login: ");
        login = read.nextLine();
        actUser = findLogin(login);
        
        if(actUser != null){
            System.out.println("[+] Digite a senha:");
            password = read.nextLine();
            
            if(password.equals(actUser.getPassword())){
                
                if(actUser.isActive()){
                   logged(actUser); 
                }else{
                    System.out.println("[!] A conta está desativada! Digite 1 se deseja recuperar seus dados ou 0 para sair:");
                    iaux = readNumber();
                    login = read.nextLine();
                    
                    if(iaux == 1){
                        actUser.active = true;
                        logged(actUser);
                    }else{
                        firstPage();
                    }
                }
            }else{
                System.out.println("[!] ERRO: Senha incorreta! Digite 1 para tentar novamente ou 0 para sair:");
                iaux = readNumber();
                login = read.nextLine();

                if(iaux == 1){
                    login();
                }
                else{
                    firstPage();
                } 
            }
        }else{
            System.out.println("[!] ERRO: Login não encontrado! Digite 1 para tentar novamente ou 0 para sair:");
            iaux = readNumber();
            login = read.nextLine();
            
            if(iaux == 1){
                login();
            }
            else{
                firstPage();
            }
        }
    }
    /*login FINISH*/
    
    /*register START*/
    public static void register(){
        User verify;
        User newUser;
        String login;
        String password1;
        String password2;
        String name;
        int iaux;
        
        System.out.println("[+] Digite o login desejado:");
        login = read.nextLine();
        verify = findLogin(login);
        if(verify == null){
            System.out.println("[+] Digite a senha desejada:");
            password1 = read.nextLine();
            System.out.println("[+] Digite a senha novamente:");
            password2 = read.nextLine();
            if(password1.equals(password2)){
                System.out.println("[+] Digite o seu nome completo:");
                name = read.nextLine();
                
                newUser = new User(login,password1,name);
                users.add(newUser);
                System.out.println("[!] Usuário cadastrado com sucesso!");
                
            }else{
                System.out.println("[!] ERRO: As senhas não correspondem! Digite 1 para tentar novamente ou 0 para sair");
                iaux = readNumber();
                login = read.nextLine();
                
                if(iaux == 1){
                    register();
                }else{
                    firstPage();
                }
            }
        }else{
            System.out.println("[!] ERRO: O login desejado já está em uso! Digite 1 para tentar novamente ou 0 para sair");
            iaux = readNumber();
            login = read.nextLine();
            
            if(iaux == 1){
                register();
            }else{
                firstPage();
            }
        }
        
    }
    /*register FINISH*/
    
    /*logged START*/
    public static void logged(User user){
        int menuOpt=-1;
        
        System.out.println("[!] Bem-vindo "+ user.getName() +"!");
        showNotify(user);
        
        while(menuOpt != 0){
            showMainMenu();
            menuOpt = readNumber();

            switch(menuOpt){
                case 1:
                    editMenu(user);
                    break;
                case 2:
                    addMenu(user);
                    break;
                case 3:
                    removeMenu(user);
                    break;
                case 4:
                     showList(user.friends);
                     break;
                case 5:
                    sendMessage(user);
                    break;
                case 6:
                    viewMessages(user);
                    break;
                case 7:
                    manageCmm(user);
                    break;
                case 0:
                    firstPage();
                    break;
                    
            }
        }
    }
    /*logged FINISH*/
    
    /*editMenu START*/
    public static void editMenu(User user){
        int editOpt;
        int[] bDate = new int[3];
        User foundUser;
        String saux;//Auxiliar para strings
        String saux2;
        Attribute newAttri;
        
        showEditMenu();
        editOpt = readNumber();
        saux = read.nextLine();
        switch(editOpt){
            case 1:
                System.out.println("[+] Digite o novo nome de perfil:");
                saux = read.nextLine();
                user.setName(saux);
                System.out.println("[!] Nome alterado com sucesso!");
                break;
            case 2:
                System.out.println("[+] Digite o nome do atributo: ");
                saux = read.nextLine();
                System.out.println("[+] Digite o valor do atributo: ");
                saux2 = read.nextLine();
                
                newAttri = new Attribute(saux,saux2);
                
                user.attributes.add(newAttri);
                System.out.println("[!] Atributo adicionado com sucesso!");
                
                break;
            case 3:
                System.out.println("[+] Digite o nome do atributo:");
                saux = read.nextLine();
                newAttri = findAttribute(user,saux);
                
                user.attributes.remove(newAttri);
                
                System.out.println("[!] Atributo removido com sucesso!"); 
                break;
            case 4:
                System.out.println("[+] Digite o novo login desejado:");
                saux = read.nextLine();
                foundUser = findLogin(saux);
                if(foundUser == null){
                    user.setLogin(saux);
                }else{
                    System.out.println("[!] ERRO: Nome de login indisponível!");
                }
                System.out.println("[!] Login alterado com sucesso!");
                break;
            case 5:
                System.out.println("[+] Digite a nova senha:");
                saux = read.nextLine();
                System.out.println("[+] Digite a senha novamente:");
                saux2 = read.nextLine();
                if(saux.equals(saux2)){
                    user.setPassword(saux);
                }else{
                    System.out.println("[!] ERRO: As senhas não correspondem!");
                }
                System.out.println("[!] Senha alterada com sucesso!");
                break;
            case 9:
                user.active = false;
                System.out.println("[!] Conta desativada com sucesso!");
                break;
        }
               
    }
    /*editMenu FINISH*/
    
    /*addMenu START*/
    public static void addMenu(User user){
        String userName;
        User foundUser;
        
        userName = read.nextLine();
        System.out.println("[+] Digite o nome do usuário a ser adicionado:");
        userName = read.nextLine();
        foundUser = findName(users,userName);
        
        if(foundUser != null){
            foundUser.invitations.add(user);
            System.out.println("[!] Solicitação de amizade enviada!");
        }else{
            System.out.println("[!] ERRO: Usuário não encontrado!");
        }
    }
    /*addMenu FINISH*/
    
    /*removeMenu START*/
    public static void removeMenu(User user){
        String name;
        User foundUser;
        
        name = read.nextLine();
        System.out.println("[+] Digite o nome do usuário a ser excluído da sua lista de amigos:");
        name = read. nextLine();
        foundUser = findName(user.friends,name);
        
        if(foundUser != null){
            user.friends.remove(foundUser);
            foundUser.friends.remove(user);
            System.out.println("[!] Usuário excluído");
        }else{
            System.out.println("[!] ERRO: Usuário não encontrado!");
        }
    }
    /*removeMenu FINISH*/
    
    /*showNotify START*/
    public static void showNotify(User user){
        //INÍCIO Sistema de solicitações de amizade
        User toAdd;
        int iaux;
        
        if(user.invitations.size()>0){
            System.out.println("[!] Você tem "+ user.invitations.size() + " solicitações de amizade!");
            while(user.invitations.size()>0){
              
                toAdd = user.invitations.remove(user.invitations.size() - 1);
                System.out.println("[!] " + toAdd.name + " enviou uma solicitação de amizade. Digite 1 para ACEITAR e 2 para NEGAR");
                iaux = readNumber();
                
                if(iaux == 1){
                    user.friends.add(toAdd);
                    toAdd.friends.add(user);
                    System.out.println("[!] Você adicionou " + toAdd.name +"!");
                }else{
                    System.out.println("[!] Solicitação de amizade negada!");
                }
            }
        }else{
            System.out.println("[!] Você não tem novas solicitações de amizade!");
        }
        //FIM Sistema de solicitações de amizade
        Message newMsg;
        if(user.notRead.size()>0){
            System.out.println("[!] Você tem "+ user.notRead.size() + " mensagens não lidas!");
            while(user.notRead.size()>0){
                newMsg = user.notRead.remove(user.notRead.size() -1);
                
                System.out.println("        [M] "+ newMsg.sender.name + " enviou: "+ newMsg.msg);
                
                user.messages.add(newMsg);
            }
        }else{
            System.out.println("[!] Você não tem novas mensagens a serem lidas!");
        }
    }
    /*showNotify FINISH*/ 
    
    /*sendMessage START*/
    public static void sendMessage(User user){
        String nm;
        String msg;
        User toSend;
        nm = read.nextLine();
        System.out.println("[+] Digite o nome da pessoa a quem você desejar enviar a mensagem:");
        nm = read.nextLine();
        toSend = findName(user.friends,nm);
        Message newMsg;
        if(toSend != null){
            System.out.println("[+] Digite a mensagem:");
            msg = read.nextLine();
            newMsg = new Message(user,msg);
            
            toSend.notRead.add(newMsg);
            System.out.println("[!] Mensagem enviada com sucesso!");
        }else{
            System.out.println("[!] ERRO: Usuário não encontrado! O usuário deve ser seu amigo para que você possa enviar mensagens!");
        }
    }
    /*sendMessage FINISH*/
    
    /*viewMessages START*/
    public static void viewMessages(User user){
        Message lastMsg;
        System.out.println("[!] Você tem "+ user.messages.size()+" mensagens!");
        for(Iterator<Message> it = user.messages.iterator(); it.hasNext();){
            lastMsg = it.next();
            System.out.println("        [M] "+ lastMsg.sender.name + " enviou: "+lastMsg.msg);
        }
    }
    /*viewMessages FINISH*/
    
    /*manageCmm START*/
    public static void manageCmm(User user){
        int cmmOpt;
        int iaux;
        String saux;
        Community newCmm;
        User foundMember;
        
        showCmmMenu();
        cmmOpt = readNumber();
        saux = read.nextLine();
        
        switch(cmmOpt){
            case 1:
                System.out.println("[+] Digite o nome da comunidade:");
                saux = read.nextLine();
                newCmm = new Community(saux);
                newCmm.adms.add(user);
                //user.communities.add(newCmm); PODE SER ÚTIL
                user.ownCmm.add(newCmm);
                communities.add(newCmm);
                System.out.println("[!] Comunidade criada com sucesso!");
                break;
            case 2:
                System.out.println("[!] Comunidades em que você é administrador:");
                listCmm(user.ownCmm);
                System.out.println("[!] Comunidades em que você é membro:");
                listCmm(user.communities);
                
                System.out.println("[+] Digite o nome da comunidade a ser visualizada:");
                saux = read.nextLine();
                newCmm = findCmm(user.ownCmm,saux);
                
                if(newCmm != null){
                    System.out.println("    [+] Digite o valor correspondente a operação a ser feita:");
                    System.out.println("        [1] Remover usuário");
                    System.out.println("        [2] Visualizar mensagens");//Done
                    System.out.println("        [3] Visualizar membros");//DONE
                    System.out.println("        [4] Enviar mensagem");//DONE
                    System.out.println("        [0] Voltar ao menu principal");
                    
                    iaux = readNumber();
                    saux = read.nextLine();
                    
                    switch(iaux){
                        case 1:
                            System.out.println("[+] Digite o nome do membro a ser removido:");
                            saux = read.nextLine();
                            foundMember = findName(newCmm.members,saux);
                            
                            if(foundMember != null){
                               newCmm.members.remove(foundMember);
                               foundMember.communities.remove(newCmm);
                               System.out.println("[!] Membro removido com sucesso!");
                            }else{
                                System.out.println("[!] ERRO: Membro não encontrado!");
                            }
                            
                            break;
                        case 2:
                            cmmMessages(newCmm);
                            break;
                        case 3:
                            System.out.println("Administradores:");
                            showList(newCmm.adms);
                            System.out.println("Membros:");
                            showList(newCmm.members);
                            break;
                        case 4:
                            cmmSendMessage(newCmm,user);
                            break;
                    }
                }else{
                    newCmm = findCmm(user.communities,saux);
                    if(newCmm != null){
                            System.out.println("    [+] Digite o valor correspondente a operação a ser feita:");
                            System.out.println("        [1] Visualizar mensagens");//Done
                            System.out.println("        [2] Visualizar membros");//DONE
                            System.out.println("        [3] Enviar mensagem");//DONE
                            System.out.println("        [0] Voltar ao menu principal");

                            iaux = readNumber();
                            saux = read.nextLine();

                            switch(iaux){
                                case 1:
                                    cmmMessages(newCmm);
                                    break;
                                case 2:
                                    System.out.println("Administradores:");
                                    showList(newCmm.adms);
                                    System.out.println("Membros:");
                                    showList(newCmm.members);
                                    break;
                                case 3:
                                    cmmSendMessage(newCmm,user);
                                    break;
                            }
                    }else{
                        System.out.println("[!] ERRO: Comunidade não encontrada!");
                    }
                }
                break;
            case 3:
                System.out.println("[+] Digite o nome da comunidade:");
                saux = read.nextLine();
                newCmm = findCmm(communities,saux);
                
                if(newCmm != null){
                    newCmm.members.add(user);
                    user.communities.add(newCmm);
                    System.out.println("[!] Você entrou na comunidade, fique atento as regras!");
                }else{
                    System.out.println("[!] ERRO: Comunidade não encontrada!");
                }
                
                break;
                
        }
        
    }
    /*manageCmm FINISH*/
    
    public static void cmmMessages(Community cmm){
        Message lastMsg;
        for(Iterator<Message> it = cmm.messages.iterator(); it.hasNext();){
            lastMsg = it.next();
            System.out.println("        [M] "+ lastMsg.sender.name + " enviou: "+lastMsg.msg);
        }
    }
    
    public static void cmmSendMessage(Community cmm, User user){
        System.out.println("[+] Digite a mensagem:");
        String saux = read.nextLine();
        Message newMsg = new Message(user,saux);
        
        cmm.messages.add(newMsg);
        System.out.println("[!] Mensagem enviada com sucesso!");
    }
    
    /*showList START*/
    public static void showList(ArrayList<User> list){
        User us = null;
        String cmp;
        int exit = 0;
        for(Iterator<User> it = list.iterator(); it.hasNext();){
            us = it.next();
            if(us.active == true){
                System.out.print("         Nome: "+us.name +" Atributos: ");
                listAttributes(us);
                System.out.println("");
            }
            
        }
    }
    /*showList FINISH*/
    
    /*listCmm START*/
    public static void listCmm(ArrayList<Community> list){
        Community cmm = null;
        for(Iterator<Community> it = list.iterator(); it.hasNext();){
            cmm = it.next();
            System.out.println("       - Nome: "+cmm.name);
        }
    }
    /*listCmm FINISH*/
    
    /*findCmm START*/
    public static Community findCmm(ArrayList<Community> list, String name){
        Community cmm = null;
        String nm;
        int exit = 0;
        for(Iterator<Community> it = list.iterator(); it.hasNext();){
            cmm = it.next();
            nm = cmm.getName();
            if(nm.equals(name)){
                exit = 1;
                break;
            }
        }
        
        if(exit==1){
            return cmm;
        }else{
            return null;
        }
    }
    /*findCmm FINISH*/
    
    /*listAttributes START*/
    public static void listAttributes(User user){
        Attribute at = null;
        int count = 1;
        for(Iterator<Attribute> it = user.attributes.iterator(); it.hasNext();){
            at = it.next();
            System.out.print(" ["+count+"] "+ at.name+ ": "+ at.value);
            count++;
        }
    }
    /*listAttributes FINISH*/
    
    /*showMainMenu START*/
    public static void showMainMenu(){
        System.out.println("[+] Digite o valor correspondente a operação a ser feita:");
        System.out.println("        [1] Editar/remover perfil");//DONE
        System.out.println("        [2] Adicionar amigo");//DONE
        System.out.println("        [3] Excluir amigo");//DONE
        System.out.println("        [4] Ver amigos");//DONE
        System.out.println("        [5] Enviar mensagem");//DONE
        System.out.println("        [6] Visualizar mensagens recebidas");//DONE
        System.out.println("        [7] Comunidades");//TODO
        System.out.println("        [0] Deslogar");
    }
    /*showMainMenu FINISH*/
    
    /*showEditMenu START*/
    public static void showEditMenu(){
        System.out.println("    [+] Digite o valor correspondente a operação a ser feita:");
        System.out.println("            [1] Alterar nome");
        System.out.println("            [2] Adicionar atributo");
        System.out.println("            [3] Remover atributo");
        System.out.println("            [4] Alterar login");
        System.out.println("            [5] Alterar senha");
        System.out.println("            [9] Desativar conta");
        System.out.println("            [0] Voltar ao menu principal");
    }
    /*showEditMenu FINISH*/
    
    /*showCmmMenu START*/
    public static void showCmmMenu(){
        System.out.println("    [+] Digite o valor correspondente a operação a ser feita:");
        System.out.println("            [1] Criar comunidade");//DONE
        System.out.println("            [2] Minhas comunidades");//DONE
        System.out.println("            [3] Entrar em uma comunidade");//DONE
        System.out.println("            [0] Voltar ao menu principal");
    }
    /*showCmmMenu FINiSH*/
    
    /*findLogin START*/
    public static User findLogin(String login){
        User us = null;
        String cmp;
        int exit = 0;
        for(Iterator<User> it = users.iterator(); it.hasNext();){
            us = it.next();
            cmp = us.getLogin();
            if(cmp.equals(login)){
                exit = 1;
                break;
            }
        }
        
        if(exit == 1){
            return us;
        }
        else{
            return null;
        }
    }
    /*findLogin FINISH*/
    
    /*findAttribute START*/
    public static Attribute findAttribute(User user,String name){
        Attribute at = null;
        String nm;
        int exit = 0;
        for(Iterator<Attribute> it = user.attributes.iterator(); it.hasNext();){
            at = it.next();
            nm = at.getName();
            if(nm.equals(name)){
                exit = 1;
                break;
            }
        }
        
        if(exit == 1){
            return at;
        }
        else{
            return null;
        }
    }
    /*findAttribute FINISH*/
    
    /*findName START*/
    public static User findName(ArrayList<User> list,String name){
        User us = null;
        String nm;
        int exit = 0;
        for(Iterator<User> it = list.iterator(); it.hasNext();){
            us = it.next();
            nm = us.getName();
            if(nm.equals(name)){
                exit = 1;
                break;
            }
        }
        
        if(exit == 1){
            return us;
        }
        else{
            return null;
        }
    }
    /*findName FINISH*/
        
    public static int readNumber(){
        int rNumber = 0;
        
        try{
            rNumber = read.nextInt();
        }catch(InputMismatchException error){
            System.out.println("ERRO: Você deve digitar um número!");
        }
            
        return rNumber;
    }    
}
