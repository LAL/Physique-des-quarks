import java.util.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;
public class application_quarks{
    static appli_quarks applic_quarks;
    public static void main(String[] args) {
	applic_quarks = new appli_quarks("Fenetre initiale.");
	applic_quarks.run();
	applic_quarks.dispose();
	applic_quarks=null;;
    }
}
class appli_quarks extends Frame{
    final int 	top_demarre = 200;
    final int 	left_demarre = 250;
    final int 	bottom_demarre = 380;
    final int 	right_demarre = 800;
    static final int 	top_cree = 190;
    static final int 	left_cree = 20;
    static final int 	bottom_cree = 230;
    static final int 	right_cree = 400;
    commentaire comm;
    private int sx;private int sy;private MouseStatic mm;
    public fenetre_particules_initiales fenetre[]=new fenetre_particules_initiales[2];
    boolean totologic=false;
    int n_fenetres=0,nb_clicks=0;boolean fini_bouge_paint=true;
    int ppmouseh,ppmousev;boolean relachee=false, pressee=false, cliquee=false;
    int i_occru=0;long toto_int=0;
    long temps_initial_en_secondes,temps_present=0,temps_prec=0,temps_minimum=1800,temps_en_sec=0;
    private SimpleDateFormat formatter; boolean retour_fen=false;
    Graphics gr;String d_ou_je_reviens="";
    boolean info_modeles=false;
    int nb_quarks;//Thread th1;
    boolean debut,toutdebut=true;
    boolean stop=false;boolean peindre;
    String comment;int i_run=0;
    Image image_h,image_modele;
    boolean terminer_demo=false;
    boolean anfang=true;
    Image image_prem_page;
    Font times14=new Font("Times",Font.PLAIN,14);
    Font times_gras_14=new Font("Times",Font.BOLD,14);
    Font times_gras_24=new Font("Times",Font.BOLD,24);int top,left,bot,right;
    double k01,k02,k12;int i_print=0;
    appli_quarks(String s){
	super(s);
        addWindowListener(new java.awt.event.WindowAdapter() {
		public void windowClosing(java.awt.event.WindowEvent e) {
		    if(comm!=null){
			comm.dispose();
			comm=null;
		    }
		    for(int i=0;i<n_fenetres;i++)
			if(fenetre[i]!=null){			    
			    fenetre[i].dispose();
			    fenetre[i]=null;
			}
		    n_fenetres=0;
		    dispose();
		    System.exit(0);  // pour ne pas laisser trainer des applications qui ne sont pas actives mais prennent de la place en mémoire
		};
	    });

	comment=" ";peindre=true;toutdebut=true;
	formatter = new SimpleDateFormat ("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
	Date maintenant=new Date();
	temps_initial_en_secondes=temps_en_secondes(maintenant);
	System.out.println("maintenant "+maintenant+" s "+temps_initial_en_secondes);
	//	setLocation(600,0);
	//setSize(200,200);
	d_ou_je_reviens="";
	top=0;left=0;bot=0+200;right=600+200;
	mm=new MouseStatic(this);
	this.addMouseListener(mm);
	 setBackground(Color.white);
	ppmouseh=100;ppmousev=200;
	Date now=new Date();
	long temps_en_sec=temps_en_secondes(now);
	System.out.println("temps_en_sec au depart "+temps_en_sec);
	temps_prec=temps_en_sec-5;
	debut=true;
	pack();setVisible(true);	
	setSize(1000,700);
	setLocation(0,0);
	gr=getGraphics();
	setVisible(true);
    }
    public long temps_en_secondes(Date nun){
	formatter.applyPattern("s");
	int s = Integer.parseInt(formatter.format(nun));
	formatter.applyPattern("m");
	int m = Integer.parseInt(formatter.format(nun));
	formatter.applyPattern("h");
	int h = Integer.parseInt(formatter.format(nun));
	//System.out.println(" h "+h+" m "+m+" s "+s);
	return (h*3600+m*60+s);
    }    
    
    public void run(){
	if(anfang){
	    anfang=false;
	    String name="C:/Users/benoit Delcourt/Desktop/j2sdk1.4.2_04/bin/quarks_premiere_page.jpg";
	    image_prem_page=createImage(400,400);
	    Graphics gTTampon=image_prem_page.getGraphics();
	    image_prem_page=Toolkit.getDefaultToolkit().getImage(name);
	    MediaTracker tracker=new MediaTracker(this);
	    tracker.addImage(image_prem_page,1); 
	    try {tracker.waitForAll(); }
	    catch (InterruptedException e){
		System.out.println(" image2 pas arrivee?");
	    }
	    //g.drawImage(image2,555,0,null);
	    //gTTampon.dispose();
	    //image.flush();
	    
	    name="C:/Users/benoit Delcourt/Desktop/j2sdk1.4.2_04/bin/quarks_modeles.jpg";
	    image_modele=createImage(300,200);
	    Graphics gTTmpn=image_modele.getGraphics();
	    image_modele=Toolkit.getDefaultToolkit().getImage(name);
	    MediaTracker tracker2=new MediaTracker(this);
	    tracker2.addImage(image_modele,1); 
	    try {tracker2.waitForAll(); }
	    catch (InterruptedException e){
		System.out.println(" image_modele pas arrivee?");
	    }
	    name="C:/Users/benoit Delcourt/Desktop/j2sdk1.4.2_04/bin/quarks_hadronisation.jpg";
	    image_h=createImage(300,200);
	    gTTmpn=image_h.getGraphics();
	    image_h=Toolkit.getDefaultToolkit().getImage(name);
	    tracker2=null;
	    tracker2=new MediaTracker(this);
	    tracker2.addImage(image_h,1); 
	    try {tracker2.waitForAll(); }
	    catch (InterruptedException e){
		System.out.println(" image_h pas arrivee?");
	    }
	    System.out.println(" image_h  "+image_h);
	    System.out.println(" image_modele  "+image_modele);
	}
	int isleep;
	System.out.println("debut run ");
	fin_de_programme:
	while (!stop){
	    Date now=new Date();
	    long temps_en_sec=temps_en_secondes(now);
	    System.out.println("temps_en_sec "+temps_en_sec);
	    if(temps_en_sec-temps_initial_en_secondes>temps_minimum){
		stop=true;break fin_de_programme; 
	    }
	    isleep=1;
	    if(debut){
		System.out.println("debut "+debut);
		if(peindre){
		    System.out.println("va peindre");
		    setVisible(true);
		    peint();
		}
		System.out.println("d_ou_je_reviens "+d_ou_je_reviens+" toutdebut "+toutdebut);
		if(d_ou_je_reviens!=""){
		    System.out.println("d_ou_je_reviens "+d_ou_je_reviens+" n_fenetres "+n_fenetres);
		    peindre=true;
		    peint();
		    n_fenetres=0;
		    d_ou_je_reviens="";
		}
		if(toutdebut){
		    gr.drawImage(image_prem_page,450,0,this);
		    terminer_demo=false;
		    if(n_fenetres==0){
			if(!cliquee){
			    fenetre[0]= new fenetre_particules_initiales(this,430,350,0,20,"meson","",0,-1);
			    n_fenetres++;
			}else
			    terminer_demo=true;
			if(!cliquee){
			    fenetre[1]=new fenetre_particules_initiales(this,430,370,0,300,"proton","",1,-1);
			    n_fenetres++;
			}else
			    terminer_demo=true;
		    }
		    for(int iii=1;iii>=0;iii--){
			if(fenetre[iii]!=null){
			    System.out.println(" iii "+iii);
			    if(fenetre[iii].debuter_new_event){
				System.out.println(" vers debuter_nouvel_event() iii "+iii);
				fenetre[iii].debuter_nouvel_event();
			    }
			    if(fenetre[iii].command==""){
				fenetre[iii].bouge_fenetre();
			    }else{
				System.out.println("commande "+fenetre[iii].command+" iii "+iii);
				fenetre[iii].traite_commande();
			    }
			}
		    }
		    if(cliquee||pressee){
			terminer_demo=true;
		    }
		    if(terminer_demo){
			cliquee=false;pressee=false;
			eraserect( gr,0,0,1000,1000);
			d_ou_je_reviens="je reviens de num_fen";	
			eliminer(0);
			eliminer(1);
			n_fenetres=0;
			Date maintenant=new Date();
			temps_prec=temps_en_secondes(maintenant);
			
			toutdebut=false;
			System.out.println(" on a detruit les fenetres de demo ");
			cliquee=false;relachee=false;pressee=false;
			ppmouseh=-100;ppmousev=-100;
			peindre=true;			
		    }
		}else{
		    totologic=false;int xi=-100;int yi=-100;
		    int kj=0;
		    System.out.println("relachee xi "+xi+" yi "+yi);//très important pour laisser le temps au traite_click, ce faux print semble nécessaire
		    //gr.drawString(" avant le xi "+xi+" yi "+yi, 100,80);
		    
		    //if(cliquee||pressee){
		    if(cliquee){
			xi=ppmouseh;yi=ppmousev;
			totologic=(xi > left_demarre)&&(xi < right_demarre)&&(yi > top_demarre)&&(yi < top_demarre+4*20);	
			//gr.drawString("totologic "+totologic+" pendant xi "+xi+" yi "+yi, 100,100);
		    }
		    //if(totologic)
		    //	fenetre[10]=null;		    
		    cliquee=false;pressee=false;
		    if(totologic){		    
			for(int i=0;i<4;i++){
			    if ((yi > top_demarre+i*20)&&(yi < top_demarre+(i+1)*20)){
				System.out.println("i "+i);
				if((i>=0)&&(i<=3)){
				    n_fenetres=1;
				    fenetre[0]=null;
				    if(i==0)
					fenetre[0]= new fenetre_particules_initiales(this,800,800,50,0,"proton","proton",0,i);
				    if(i==1)
					fenetre[0]= new fenetre_particules_initiales(this,800,800,50,0,"meson","proton",0,i);
				    if(i==2||i==3)
					fenetre[0]= new fenetre_particules_initiales(this,800,700,50,0,"quark","antiquark",0,i);
				    
				    //fenetre[0].setVisible(false);fenetre[0].setVisible(true);
				    System.out.println("cree, i"+i);
				    if(retour_fen)
					fenetre[101]=null;
				}
				debut=false;
				relachee=false;
				//peindre=true;
			    }
			    if(retour_fen)
				fenetre[103]=null;
			}
		    }
		}
	    }else{
		System.out.println("applet n_fenetres "+n_fenetres);
		for(int i=0;i<n_fenetres;i++){
		    i_run++;if(i_run>=6)i_run=0;
		    System.out.println(" i_run "+i_run);
		    if(!fenetre[i].stopper&&!fenetre[i].desactiver){
			if(!fenetre[i].pressee||fenetre[i].staccato){
			    //System.out.println("fenetre[i].debuter_new_event "+fenetre[i].debuter_new_event);
			    d_ou_je_reviens="";
			    peindre=false;
			    fenetre[i].gerer_l_evenement();
			    if(fenetre[i].le_virer){
				toutdebut=fenetre[i].command=="Revenir a la fenetre initiale avec infos";
				d_ou_je_reviens=fenetre[i].command;
				fenetre[i].command="";
				System.out.println("*i "+i+" n_fenetres "+n_fenetres+" command "+fenetre[i].command);
				if(toutdebut){
				    terminer_demo=false;
				    //demo_faite=false;
				    //dejapaint=false;
				    eraserect(gr,0,0,1000,1600);
				}
				peindre=true;
				relachee=false;
				pressee=false;
				cliquee=false;
				if(comm!=null)
				    comm.elimine();
				n_fenetres=0;
				eliminer(i);
				break;
			    }
			    //if(fenetre[i].desactiver&&!fenetre[i].deja_paint&&!occupied)
			    System.out.println("avant break ");
			    peindre=true;
			    break;
			    //}
			    //	fini_bouge_paint=true;
			}
		    }
		}
		if(peindre)
		    peint();
	    }
	    //System.out.println("avant try catch isleep"+isleep);
	    try {Thread.sleep(isleep);}
	    catch (InterruptedException signal){
		//System.out.println("catch ");
	    }
	    //System.out.println("run de nouveau debut"+debut+" stop "+stop);
	    //System.out.println("apres try catch ");
	}
	peindre=true;
	peint();
	for(int i=0;i<n_fenetres;i++)
	    fenetre[i].dispose();
	//for(int i=0;i<10;i++){
	//  gr.drawString("TEMPS MAXIMUM EXPIRE",100,100);
	//  gr.drawString("FIN DU PROGRAMME",100,140);
	//  gr.drawString("POUR REVENIR A INTERNET, SUPPRIMEZ CETTE FENETRE.",100,180);
	//}
	System.out.println("fin du programme");
	if(comm!=null)
	    comm.elimine();
	dispose(); System.exit(0);
    }
    void eliminer(int num_ens){
	if(fenetre[num_ens].i_demarre==2){
	    comm.dispose();
	    comm=null;
	}
	if(fenetre[num_ens].statistique!=null){
	    fenetre[num_ens].statistique.dispose();
	    fenetre[num_ens].statistique=null;
	}	
	if(fenetre[num_ens].cqach!=null){
	    fenetre[num_ens].cqach.dispose();
	    fenetre[num_ens].cqach=null;
	}
	if(fenetre[num_ens].command=="Sortir du programme")
	    stop=true;
	debut=true;peindre=true;
	n_fenetres=0;
	System.out.println("n_fenetres "+n_fenetres+"num_ens "+num_ens);
	cliquee=false;
	relachee=false;
	pressee=false;
	
	//fenetre[num_ens].dispose();
	//fenetre[num_ens]=null;
	
	fenetre[num_ens].dispose();
	fenetre[num_ens]=null;

	System.out.println("apres dispose() ");
    }
    public void peint(){
	//System.out.println("entree peint");
	if(peindre){
	    System.out.println("tutu cliquee"+cliquee+" relachee "+relachee);
	    eraserect( gr,0,0,1000,1000);
	    
	    if(!stop){
		if(!toutdebut){
		    System.out.println("toto ouich");
		    gr.setFont(times_gras_14);
		    gr.setColor(Color.red);
		    gr.setFont(times_gras_24);
		    gr.drawString("Choisissez une reaction entre particules.",left_demarre, top_demarre-10);		    
		    gr.setFont(times14);
		    paintrect_couleur(gr,top_demarre,left_demarre,bottom_demarre,right_demarre,Color.red);
		    gr.setFont(times_gras_24);
		    gr.setColor(Color.black);
		    gr.setColor(Color.blue);gr.setFont(times14);
		    drawline_couleur(gr,left_demarre, top_demarre+20, right_demarre, top_demarre+20, Color.red);
		    gr.drawString("Diffusion proton-proton.",left_demarre+10, top_demarre+14);//0
		    gr.drawString("Diffusion pi-proton ",left_demarre+10, top_demarre+34);//1
		    drawline_couleur(gr,left_demarre, top_demarre+40, right_demarre, top_demarre+40, Color.red);
		    gr.drawString("Creation de hadrons par une paire quark-antiquark, referentiel en mouvement.",left_demarre+10, top_demarre+54);//2
		    drawline_couleur(gr,left_demarre, top_demarre+60, right_demarre, top_demarre+60, Color.red);
		    gr.drawString("Creation de hadrons par une paire quark-antiquark, referentiel du centre de masse.",left_demarre+10, top_demarre+74);//2
		    drawline_couleur(gr,left_demarre, top_demarre+80, right_demarre, top_demarre+80, Color.red);
		    //gr.drawString("Systeme B-Bbar et violation de C.P.",left_demarre+10, top_demarre+114);//5
		    drawline_couleur(gr,left_demarre, top_demarre+100, right_demarre, top_demarre+100, Color.red);
		    drawline_couleur(gr,left_demarre,top_demarre+120, right_demarre, top_demarre+120, Color.red);
		    drawline_couleur(gr,left_demarre,top_demarre+140, right_demarre,top_demarre+140, Color.red);
		    drawline_couleur(gr,left_demarre,top_demarre+160, right_demarre,top_demarre+160, Color.red);
		    peindre=false;
		    cliquee=false;
		}
	    }else{
		eraserect(gr,0,0,1000,1000);
		gr.setFont(times_gras_24);gr.setColor(Color.black);
		for(int i=0;i<10;i++){
		    if(temps_en_sec-temps_initial_en_secondes>temps_minimum)
			gr.drawString("TEMPS MAXIMUM EXPIRE",100,100);
		    else{
			gr.drawString("FIN DU PROGRAMME",100,100);
			gr.drawString("POUR REVENIR A INTERNET, SUPPRIMEZ CETTE FENETRE.",100,150);
		    }
		}
	    }
	}
	peindre=false;
    }

    void drawline_couleur(Graphics g,int xin, int yin, int xfin, int yfin, Color couleur){
	g.setColor(couleur);g.drawLine(xin,yin,xfin,yfin);
    }
    void eraserect(Graphics g, int top, int left, int bot, int right){
	int x,y,width,height;
	x=left;y=top;width=right-left;height=bot-top;
	//System.out.println("x "+x+" y "+y+" width "+width+" height "+height);
	if(g!=null&&n_fenetres!=0)
	    g.setColor(Color.white);
	if(g!=null&&n_fenetres!=0)
	    g.fillRect(x,y,width,height);
    }
    
    void paintrect_couleur(Graphics g,int top, int left, int bot, int right, Color couleur){
	int x,y,width,height;
	x=left;y=top;width=right-left;height=bot-top;
	g.setColor(couleur);g.drawRect(x,y,width,height);
    }    
    
    public void traite_click(){
	Date maintenant=new Date();
	temps_present=temps_en_secondes(maintenant);
	toto_int=temps_prec;
	if(cliquee){
	    if(temps_present-temps_prec<1){
		System.out.println("temps_present "+temps_present+"temps_prec "+temps_prec);
		cliquee=false;pressee=false;
	    }
	    temps_prec=temps_present;
	}
	System.out.println("&&&&&temps_present "+temps_present+"temps_prec "+temps_prec+" ppmouseh "+ppmouseh+" ppmousev "+ppmousev+" cliquee "+cliquee);
	temps_initial_en_secondes=temps_present;
	//if(nb_clicks!=0)
	//	fenetre[100]=null;
	if(cliquee&&!toutdebut&&n_fenetres!=0){
	    cliquee=false;pressee=false;relachee=false;
	    if(fenetre[0].numero_evt>0)
		for(int ik=0;ik<n_fenetres ;ik++){    
		    fenetre[ik].le_virer=true;
		    fenetre[ik].command="Revenir a la fenetre principale";
		}
	}
    }
    class MouseStatic extends MouseAdapter{
	appli_quarks appelant;
	public MouseStatic (appli_quarks a){
	    appelant=a;
	}
	public void mouseClicked(MouseEvent e){
	    ppmouseh=e.getX();ppmousev=e.getY();
	    cliquee=true;pressee=false;relachee=true;
	    System.out.println("cliquee dans MouseStatic "+cliquee);

	    traite_click();
	    
	    
	}
	public void mousePressed(MouseEvent e){
	    //ppmouseh=e.getX();ppmousev=e.getY();
	    cliquee=false;pressee=true;relachee=false;
	    System.out.println("pressee dans MouseStatic "+pressee);
	    traite_click();
	}
	public void mouseReleased(MouseEvent e){
	    //ppmouseh=e.getX();ppmousev=e.getY();
	    cliquee=false;pressee=true;relachee=true;
	    System.out.println("relachee dans MouseStatic "+relachee);
	    traite_click();
	}
    }
    void ecriture_aide(int i_demar){
	if(comm==null)
	    comm=new commentaire("commentaires");
	comm.ecrit_aide(i_demar);
    }
    class commentaire extends Frame{
	final int top=300;final int left=120;final int bottom = 565;final int right = 790;
	Graphics grp_c;
	commentaire(String s){
	    super(s);
	    System.out.println("cree graphe "+s);
	    addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
			//while(occupied){
			//}
			dispose();
			comm=null;
		    };
	    });
	    pack();//setVisible(true);		    
	    setSize(right-left,bottom-top);
	    setLocation(left,top);
	    //setLocation(left,top);
	    //setVisible(false);
	    grp_c=getGraphics();
	}
	public void ecrit_aide(int i_demar ){
	   System.out.println(" ecrit_aide()"); 
	   setVisible(true);
	   if(info_modeles){
	       setSize((int)((right-left)*0.85),(int)((bottom-top)*1.35));	       
	       System.out.println(" image_modele "+image_modele);	       
	       grp_c.drawImage(image_modele,20,30,this);
	   }else if(i_demar>=2){
	       setSize(right-left,bottom-top);
	       System.out.println(" image_h "+image_h);	       
		grp_c.drawImage(image_h,20,30,this);
	   }
	}
	public void elimine(){
	      dispose();
	}
    }
}
class fenetre_particules_initiales extends Frame implements ActionListener{
    static final double pi=3.141592652;couleur coulor_quark;
    static final Color rouge=new Color(255,0,0),vert=new Color(0,255,0),jaune=new Color(255,255,0),bleu=new Color(0,0,255),rouge_pale=new Color(255,111,111),vert_pale=new Color(111,255,111),jaune_pale=new Color(255,255,111),bleu_pale=new Color(111,111,255);
    boolean image_h_deja_la=false,le_virer=false;int n_info_modeles=0;
    boolean totologic=false,desactiver=false;
    int compere[]=new int[100];
    Graphics grph;
    Image crop_copy[]=new Image[10];
    Graphics gTampon_crop_copy[]=new Graphics[10];
    point pos0,pos1;
    int compteur_avant_erreur=0;
    long numero_evt=0;int n_refus_baryon=0,n_refus_paires=0;
    ligne_coloree ligne_relecture,ligne_relecture0,ligne_relecture1,ligne_relecture2;
    MenuItem itpp1,itf1,itr4;boolean echange_quarks=true;
    boolean limite_d_ecriture_depassee=false,plus_rien_a_voir=false;
    int ix_drwl=0,iy_drwl=0;float d_drwl=0;double dx=0.,dy=0.;
    boolean dessine_drwl=true; int deja_dessine_drwl=0;double angle_vit=0.;
    double fact_drwl=0,tg_drwl=0.;point posi_drwl,posf_drwl;int iss_drwl=1;boolean continuez_drwl;
    int num_had_init0[][]=new int[3][4];
    int num_had_init1[][]=new int[3][4];
    point pos_had0[]=new point[4];
    point pos_had1[]=new point[4];
    int assemblage_choisi=-1;
    double distance_carre_totale=0;
    double coco=0.,cucu=0.,cici=0.,cece=0.,caca=0.;
    ce_qui_a_change cqach;boolean voir_ce_qui_a_change=false;    int i_pt_orange=-1;
    int liste_des_elimines[]=new int [6];
    int nb_images_pedago=10;boolean parite_pedago=true;
    int nb_essai[]=new int[8];int nb_de_pas_a_la_fin=0;
    int abscisse_initiale=250;boolean sortir_de_cette_boucle=false;
    int ordonnee_initiale=0;int n_essais_paires_2_quarks_restant=0;
    point_int tutu_int,tete_int;
    int diff_y_labo_cdm=0; double x_du_cdm=0;
    double masse_quark,masse_meson,masse_proton,m_pi_meson,m_baryon; 
    double fact_zoom=0.5;
    point speed_q0,speed_q1;
    double m_quarks=1.;int n_baryons=0,n_mesons=0;
    boolean declencher_nouvel_event=false,evenement_termine_exterieurement=false,debuter_new_event=true,fin_provisoire=false;
    boolean debut_event=false,fin_event_naturelle=false,du_nouveau=false,en_train_de_lire=false;
    boolean attendre_signal_new_event=false;
    int ccccc=0;
    int p_quark_miny=1000, p_quark_maxy=-1000;
    boolean relire_l_evenement=false;boolean baryon_cree=false;
    int nb_evts_purs_hadrons=0;boolean accepte_bouge=false;
    int evenement_precedent[]=new int [40000];int iecr_evt_prec=0,ilec_evt_prec=0,pointeur_debut=0; 
    int nature_particule=0,nature_particule2=0;point_int point_int_relecture;point_int point_int_relecture2;
    bild image_meson,image_baryon,image_puits_meson,image_puits_proton,image_4_rouge,image_4_noire,image_8_orange;
    bild image_quark[]=new bild[3];
    resume_intermediaire resume_int; 
    double d_carre=0.,delta_sec_deg=0.,dp_sec_deg=0.;
    boolean solu_impossible[]=new boolean[10];int nb_dt_sans_paint=1;
    boolean a_cree_une_paire=false,ne_pas_creer_de_paire_au_prochain_passage=false;
    quadrimoment quad_emis_0,quad_emis_1;
    boolean melange=false,toto_melange=false;boolean melange_p=false;boolean melange_initial=false;
    point_int pt_dessine;
    int nb_couleur_q_qb=0,nb_gluons_event=0;
    //double dist2_min_creation_paire=100.*100.,inv_mass_ref=0.,dist2_hadronisation=100.*100.,dist2_baryon=300.*300.;
    double dist2_min_creation_paire=100.*100.,inv_mass_ref=0.,dist2_hadronisation=120.*120.,dist2_baryon=300.*300.;
    double vit_part_mini=30.;
    double energie_init=0.;
    quadrimoment quad_quarks_seuls,quad_quarks_hadrons,q_somme,quad_quarks_seuls_prec,quad_quarks_hadrons_prec;
    point pos_centre,v_du_cdm;
    int i_print=0;int num_fen;
    boolean cdm=true,creant=false;
    couleur coul[]=new couleur[3];
    int diametre_quarks,diametre_quarks_pedago=2;	
    boolean faire_stat=false,staccato=false,mode_continu=false;
    boolean ne_voir_que_les_gluons_reels=false;
    int n_particules_initiales=0,n_particules_finales=0;double dist_lim_pot=120.0;
    int nb_de_coups_pour_rien=0;
    private MouseMotion motion;
    private MouseStatic mm;    graphique statistique;
    boolean alerte=false,recommencement=false,peignant=false;
    double raideur;int ppmouseh,ppmousev;int jjk=0;
    point vatf,watf,toto,titi,tutu,tata,tete;
    point_int vatf_int,toto_int,tata_int;
    boolean est_demarre=false;
    boolean osc_harm=true,osc_harm_puits=false,osc_harm_limite=false,quantique=false;
    boolean antiqqq=false;
    appli_quarks subject;boolean relachee=false, pressee=false, cliquee=false,draguee=false;
    MenuBar mb=new MenuBar();
    private TextField tf; boolean stopper=false;int top,left,bot,right;
    String command,comment,str,str1; boolean retour_traite_commande,creation_paire_deja_faite=false;
    Color gris_pale;boolean diffusion=false,creation_completee=false;
    point_int r_depart0,r_depart1;int n_passages=0,size_x=0,size_y=0,loc_x=0,loc_y=0;
    point dp_p_sec_deg;
    boolean occupied=false,occupied_interrupt=false;
    double vitsxa,vitsxb,vitsya,vitsyb,vitsx0=0,vitsx1=0,vitsy1=0,vitsy2=0;
    int diametre1,diametre2;int i_demarre;
    double dt_fenetre;double dt_fenetre0=0.02;
    double y_depart_a=320,y_depart_b=300;
    int diametre_quark,diametre_meson=32,diametre_meson_pedago=20,diametre_baryon=48,diametre_baryon_pedago=30,diametre_puits_meson,diametre_puits_proton;
    point_int deplace_pour_plan;
    int indice_pedago=0;
    point_int positions_quarks_pedago[]=new point_int[20];
    int n_quarks_pedago=0;
    point point_nul;point_int point_nul_int,point_mil_int;
    public particule_initiale part_comp[]=new particule_initiale[40];       
    quadrimoment quadrimoment_tot_init;
    int couleur_sauv=0;
    public particule_finale part_comp_finale[]=new particule_finale[30];
    public fenetre_particules_initiales(appli_quarks a,int size_xx,int size_yy,int loc_xx,int loc_yy,String s,String s1,int num_fen1,int i_demarre1){
	super(s+" "+s1);
	str=s;str1=s1;

	if(i_demarre1!=-1){
        addWindowListener(new java.awt.event.WindowAdapter() {
		public void windowClosing(java.awt.event.WindowEvent e) {
		    le_virer=true;
		    command="Revenir a la fenetre principale";
		};
	    });
	}
	occupied=true;
	size_x=size_xx;size_y=size_yy;
	loc_x=loc_xx;loc_y=loc_yy;
	top=loc_y;left=loc_x;bot=loc_y+size_y;right=loc_x+size_x;
	speed_q0=new point(0.,0.);speed_q1=new point(0.,0.);v_du_cdm=new point(0.,0.);
	pos_centre=new point((right-left)/2.,(bot-top)/2.);
	i_demarre=i_demarre1;
	if(i_demarre==2){
	    cdm=false;
	    nb_images_pedago=9;
	}
	if(i_demarre==3){
	    fact_zoom=1;
	    cdm=true;
	    i_demarre=2;
 	}
	if(i_demarre==0){
	    num_had_init0[0][0]=0;num_had_init1[0][0]=1;
	    num_had_init0[1][0]=0;num_had_init1[1][0]=1;
	    num_had_init0[2][0]=0;num_had_init1[2][0]=1;
	    num_had_init0[0][1]=0;num_had_init1[0][1]=1;
	    num_had_init0[1][1]=1;num_had_init1[1][1]=0;
	    num_had_init0[2][1]=0;num_had_init1[2][1]=1;
	    num_had_init0[0][2]=0;num_had_init1[0][2]=1;
	    num_had_init0[1][2]=0;num_had_init1[1][2]=1;
	    num_had_init0[2][2]=1;num_had_init1[2][2]=0;
	    num_had_init0[0][3]=0;num_had_init1[0][3]=1;
	    num_had_init0[1][3]=1;num_had_init1[1][3]=0;
	    num_had_init0[2][3]=1;num_had_init1[2][3]=0;
	}
	if(i_demarre==0||i_demarre==1){
	    for(int i=0;i<4;i++){
		pos_had0[i]=new point(0.,0.);
		pos_had1[i]=new point(0.,0.);
	    }
	    osc_harm=false;osc_harm_puits=true;osc_harm_limite=false;quantique=false;
	}
	command="";
	nb_essai[0]=10;nb_essai[1]=20;nb_essai[2]=30;
	nb_essai[3]=-10;nb_essai[4]=-20;nb_essai[5]=-30;
	nb_essai[6]=4;nb_essai[7]=5;
	dt_fenetre0=0.04;
	//dt_fenetre0=0.2;
	if(i_demarre>1||i_demarre==-1)
	    dt_fenetre0=0.02;
	dt_fenetre=dt_fenetre0;
	subject=a;num_fen=num_fen1;
	masse_quark=1;masse_meson=5*masse_quark;masse_proton=5*masse_quark;
	if(i_demarre==-1){
	    diametre_puits_meson=160;diametre_puits_proton=200;
	}else{
	    diametre_puits_meson=80;diametre_puits_proton=120;
	}
	m_pi_meson=masse_meson;m_baryon=masse_proton;
	if(s=="quark"||s=="antiquark")
	    diametre1=diametre_quark;
	else if(s=="meson")
	    diametre1=diametre_puits_meson;
	else if(s=="proton")
	    diametre1=diametre_puits_proton;
	if(s1=="quark"||s1=="antiquark")
	    diametre2=diametre_quark;
	else if(s1=="meson")
	    diametre2=diametre_puits_meson;
	else if(s1=="proton")
	    diametre2=diametre_puits_proton;
	itpp1=new MenuItem("Relire l'evenement pas a pas.");
	itpp1.setEnabled(false);
	itf1=new MenuItem("Voir le graphe final de l'evenement en plein ecran.");
	itf1.setEnabled(false);
	itr4=new MenuItem("Sans echange de quarks entre les hadrons");
	if(!(s1=="")){
	   vitsya=20;vitsyb=20.;
	   vitsxa=20;vitsxb=-20.;
	   if(i_demarre==2){
	       vitsxa=40.;vitsxb=40.;
	       vitsya=40;;vitsyb=-40.;
	   }
       }else
	   vitsya=0;vitsyb=0.;
       vatf=new point(0,0);watf=new point(0,0);
       toto=new point(0,0);titi=new point(0,0);tutu=new point(0,0);tata=new point(0,0);tete=new point(0,0);
       pos0=new point(0.,0.);pos1=new point(0.,0.);
       vatf_int=new point_int(0,0);toto_int=new point_int(0,0);tata_int=new point_int(0,0);
       gris_pale=new Color(101,101,101);comment="";
       dp_p_sec_deg=new point(0.,0.);
       deplace_pour_plan=new point_int(400,0);
       for(int i=0;i<10;i++)
	   solu_impossible[i]=false;
       pt_dessine=new point_int(0,0);
       point_int_relecture=new point_int(0,0);
       point_int_relecture2=new point_int(0,0);
       ligne_relecture=new ligne_coloree(-1,point_int_relecture,point_int_relecture);
       ligne_relecture0=new ligne_coloree(-1,point_int_relecture,point_int_relecture);
       ligne_relecture1=new ligne_coloree(-1,point_int_relecture,point_int_relecture);
       ligne_relecture2=new ligne_coloree(-1,point_int_relecture,point_int_relecture);
       r_depart0=new point_int(size_x/2,size_y/2);
       r_depart1=new point_int(0,0);
       point_nul=new point(0,0);
       point_nul_int=new point_int(0,0);
       posi_drwl=new point(0,0); posf_drwl=new point(0,0);
       tutu_int=new point_int(0,0);tete_int=new point_int(0,0);
       point_mil_int=new point_int(-1000,-1000);
       q_somme=new quadrimoment(0.,0.,point_nul);
       quadrimoment_tot_init=new quadrimoment(0.,0.,point_nul);
       quad_quarks_seuls=new quadrimoment(0.,0.,point_nul);
       quad_quarks_hadrons=new quadrimoment(0.,0.,point_nul);
       quad_quarks_seuls_prec=new quadrimoment(0.,0.,point_nul);
       quad_quarks_hadrons_prec=new quadrimoment(0.,0.,point_nul);
       ne_voir_que_les_gluons_reels=false;
       alerte=false;
       n_passages=0;nb_de_coups_pour_rien=0;
	if(i_demarre==2)
	    subject.ecriture_aide(i_demarre);
       pack();setVisible(true);	
       System.out.println("size_x "+size_x+" size_y "+size_y+"loc_x "+loc_x+" loc_y "+loc_y);
       grph=getGraphics();
       setSize(size_x,size_y);setLocation(loc_x,loc_y);
       if(i_demarre==2)
	   resume_int=new resume_intermediaire(cdm);
       cqach=new ce_qui_a_change("changements ");
       coul[0]=new couleur(0);coul[1]=new couleur(1);coul[2]=new couleur(2);
       if(s1!="")
	   diametre_quarks=16;
       else
	   diametre_quarks=32;
       if(i_demarre==2)
	   diametre_quarks_pedago=8;
       if(i_demarre==2){
	   image_meson=new bild(diametre_meson,diametre_meson_pedago,-1,false);
	   image_baryon=new bild(diametre_baryon,diametre_baryon_pedago,-1,false);
       }else{
	   image_puits_meson=new bild(diametre_puits_meson,0,-1,false);
	   image_puits_proton=new bild(diametre_puits_proton,0,-1,false);
       }
       image_4_rouge=new bild(4,0,0,false);
       image_4_noire=new bild(4,0,-2,false);
       image_8_orange=new bild(10,0,-3,false);
       for(int i=0;i<3;i++)
	   if(i_demarre==2)
	       image_quark[i]=new bild(diametre_quarks,diametre_quarks_pedago,i,true);
	   else
	       image_quark[i]=new bild(diametre_quarks,0,i,true);

	for(int i=0;i<20;i++)
	    positions_quarks_pedago[i]=new point_int(0,0);
	mm=new MouseStatic(this);
	this.addMouseListener(mm);
	motion=new MouseMotion(this);staccato=false;
	this.addMouseMotionListener(motion);
	diffusion=(s1!="");
	y_depart_a=(bot-top)/4;y_depart_b=20+(bot-top)/4;
	if(i_demarre<=1)
	    y_depart_a=80+(bot-top)/4;;
	barre_des_menus();
	setSize(size_x,size_y);setLocation(loc_x,loc_y);
	//       part_comp[300]=null;
	//
	debuter_nouvel_event();
	//if(i_demarre==2)
	//   staccato=true;
	System.out.println(" demarrage vers bouge_fenetre() ");
	bouge_fenetre();
	occupied=false;
    }
    private void cree_particules(String stri){
	creant=true;
	if(i_demarre==2){
	    if(!cdm){
		r_depart0=new point_int(30,(int)(y_depart_a-50));
		r_depart1=new point_int(30,(int)(y_depart_b-50));
	    }else{
		r_depart0=new point_int(220,(int)(y_depart_a));
		r_depart1=new point_int(220,(int)(y_depart_b));	
	    }
	}else if(i_demarre!=-1){
	    r_depart0=new point_int(300,(int)(y_depart_a)-100+20);
	    r_depart1=new point_int(600,(int)(y_depart_b)-100);
	    diff_y_labo_cdm=300;
	}
	//r_depart1.print("y_depart_a "+y_depart_a+"y_depart_b "+y_depart_b+"r_depart1");
	vitsy1=vitsya;vitsy2=vitsyb;
	vitsx0=vitsxa;vitsx1=vitsxb;
	if(cdm){
	    vitsx1=-vitsx0;vitsy2=-vitsy1;
	    /*
	    if(i_demarre==2){
		vitsx0/=4;vitsx1/=4; 
	    }
	    */ 
	}
	if(!((i_demarre==2)&&!cdm)){
	    vitsy1=0.;vitsy2=0.;
	}
	System.out.println("vitsya "+vitsya+"vitsyb "+vitsyb+"vitsxa "+vitsxa+"vitsxb "+vitsxb);
	System.out.println("vitsy1 "+vitsy1+"vitsy2 "+vitsy2+"vitsx0 "+vitsx0+"vitsx1 "+vitsx1);
	point geschw0=new point(vitsx0,vitsy1);
	if(n_particules_initiales==0){
	    part_comp[n_particules_initiales]=new particule_initiale(r_depart0,diametre1,geschw0,stri,n_particules_initiales,-1);
	    r_depart0.print(" r_depart0 ");
	    part_comp[0].pos_part.print(" stri "+stri+" part_comp[0].pos_part. "); 
	}else{
	    point geschw1=new point(vitsx1,vitsy2);
	    if(i_demarre==2){
		part_comp[n_particules_initiales]=new particule_initiale(r_depart1,diametre2,geschw1,stri,n_particules_initiales,-1);
		if(!part_comp[n_particules_initiales].nouvelle)
		    part_comp[654]=null;
	    }else{
		    geschw1.assigne_facteur(geschw0,-1.);
		    part_comp[n_particules_initiales]=new particule_initiale(r_depart1,diametre2,geschw1,stri,n_particules_initiales,-1);
		    part_comp[1].vit_part.x=-part_comp[0].vit_part.x*part_comp[0].masse/part_comp[1].masse;
		if(i_demarre!=-1){
		    v_du_cdm.x=-part_comp[1].vit_part.x;
		}
	    }
	    inv_mass_ref=(geschw0.longueur()*geschw1.longueur()-geschw0.scalaire(geschw1))/2.;
	}
	part_comp[n_particules_initiales].initiale=true;
	n_particules_initiales++;creant=false;
	if(n_particules_initiales==2){
	    x_du_cdm=(r_depart0.x*part_comp[0].masse+r_depart1.x*part_comp[1].masse)/(part_comp[0].masse+part_comp[1].masse);
	    abscisse_initiale=(int)x_du_cdm;
	    ordonnee_initiale=(int)((r_depart0.y*part_comp[0].masse+r_depart1.y*part_comp[1].masse)/(part_comp[0].masse+part_comp[1].masse));
	    //System.out.println("vitsya "+vitsya+"vitsyb "+vitsyb+"vitsxa "+vitsxa+"vitsxb "+vitsxb);
	    //System.out.println("vitsy1 "+vitsy1+"vitsy2 "+vitsy2+"vitsx0 "+vitsx0+"vitsx1 "+vitsx1);
	}
	diametre_quark=part_comp[0].quark[0].diam_quark;	
    }
    private void barre_des_menus(){
	Menu actions2= new Menu("Actions");
	if(i_demarre!=-1){
	    MenuItem itep3=new MenuItem("Declencher un evenement");
	    actions2.add(itep3);
	    itep3.addActionListener(this);
	    if(i_demarre==2){
		MenuItem itp1=new MenuItem("Declencher un evenement pas a pas, par clics ");
		actions2.add(itp1);
		itp1.addActionListener(this);
	    //itpp1=new MenuItem("Relire l'evenement.");
		actions2.add(itpp1);
		itpp1.addActionListener(this);
	    }
	    if(subject.n_fenetres==1){
		MenuItem itep3a=new MenuItem("Voir les evenements en continu");
		actions2.add(itep3a);
		itep3a.addActionListener(this);
		if(staccato){
		    MenuItem itet3=new MenuItem("Terminer cet evenement");
		    actions2.add(itet3);
		    itet3.addActionListener(this);
		}
	    }
	    if(diffusion){
		MenuItem itep10;
		if(i_demarre!=2)
		    itep10=new MenuItem("faire une statistique de l'angle de diffusion.");
		else
		    itep10=new MenuItem("faire une statistique du nombre de hadrons.");
		actions2.add(itep10);
		itep10.addActionListener(this);
	    }
	    if(i_demarre==2){
		/*
		MenuItem itep10a=new MenuItem("pour les gluons, ne voir que les reels.");
		actions2.add(itep10a);		
		itep10a.addActionListener(this);
		actions2.add(itf1);
		itf1.addActionListener(this);
		itf1.setEnabled(false);
		*/
	    }
	    if(subject.n_fenetres>1){
		MenuItem itep5=new MenuItem("desactiver ou reactiver la fenetre");
		actions2.add(itep5);
		itep5.addActionListener(this);
	    }
	}else{
	    MenuItem itep3=new MenuItem("Nouvelles conditions initiales");
	    actions2.add(itep3);
	    itep3.addActionListener(this);
	    MenuItem ita1=new MenuItem("Revenir aux conditions initiales");
	    actions2.add(ita1);
	    ita1.addActionListener(this);
	}
	mb.add(actions2);
	if(diffusion){
	    Menu modifier= new Menu("Modifier les parametres.");
	    MenuItem itep6=new MenuItem("augmenter la vitesse initiale de 10 pour cent");
	    MenuItem itep7=new MenuItem("diminuer la vitesse initiale de 10 pour cent");
	    MenuItem itep8=new MenuItem("augmenter la distance transverse initiale de 10 pour cent");
	    MenuItem itep9=new MenuItem("diminuer la distance transverse initiale de 10 pour cent");
	    modifier.add(itep6);
	    itep6.addActionListener(this);
	    modifier.add(itep7);
	    itep7.addActionListener(this);
	    modifier.add(itep8);
	    itep8.addActionListener(this);
	    modifier.add(itep9);
	    itep9.addActionListener(this);
	    mb.add(modifier);
	}

	if(i_demarre!=2){
	    Menu modele= new Menu("Modele");
	    MenuItem itr1=new MenuItem("Oscillateur harmonique");
	    MenuItem itr2=new MenuItem("Oscillateur harmonique plus puits");
	    MenuItem itr3=new MenuItem("Oscillateur harmonique limite a une distance maxi");
	    modele.add(itr1);
	    modele.add(itr2);
	    modele.add(itr3);
	    itr1.addActionListener(this);
	    itr2.addActionListener(this);
	    itr3.addActionListener(this);
	    if(i_demarre==0||i_demarre==1){
		modele.add(itr4);
		itr4.addActionListener(this);
	    }
	    MenuItem itr5=new MenuItem("Information sur ces modeles");
	    modele.add(itr5);
	    itr5.addActionListener(this);
	    if(i_demarre==-1){
		MenuItem iteq1=new MenuItem("Puits, Mecanique quantique");
		modele.add(iteq1);
		iteq1.addActionListener(this);
	    }
	    mb.add(modele);
	}
	if(i_demarre!=-1){
	    Menu exit= new Menu("Sortir");
	    MenuItem iten1=new MenuItem("Revenir a la fenetre principale");
	    exit.add(iten1);iten1.addActionListener(this);
	    MenuItem iten2=new MenuItem("Revenir a la fenetre initiale avec infos");
	    exit.add(iten2);iten2.addActionListener(this);
	    MenuItem iteb1a=new MenuItem("stopper ou reprendre");
	    exit.add(iteb1a);
	    iteb1a.addActionListener(this);
	    MenuItem iten3=new MenuItem("Sortir du programme");
	    exit.add(iten3);iten3.addActionListener(this);
	    mb.add(exit);
	}

	setMenuBar(mb);
	pack();
	setVisible(true);
    }
    void effacer(){
	if(i_demarre==2)
	    if(!debut_event)
		if(!cdm)
		    subject.eraserect(grph,0,0,(bot-top)/2+100,(right-left)/2);//%%
		else
		    subject.eraserect(grph,0,0,(bot-top)/2,(right-left)/2);
	    else
		subject.eraserect(grph,0,0,bot-top,right-left);
	else if(i_demarre==-1)
	    subject.eraserect(grph,0,0,bot-top,right-left);
	else{
	    if (!debut_event)
		subject.eraserect(grph,0,150,bot-top,right-left);
	    else{
		subject.eraserect(grph,0,0,bot-top,right-left);
		if(i_demarre!=2){
		    grph.setFont(subject.times_gras_24);grph.setColor(Color.blue);
		    grph.drawString("Referentiel: ",10,80);
		    grph.drawString("Centre de ",10,160);
		    grph.drawString(" masse. ",10,200);
		    grph.drawString("Cible fixe. ",10,460);
		    grph.setFont(subject.times_gras_14);
		}
	    }
	}
    }
    public void paint(){
	//grph.drawString(comment,xcom, ycom);
	if(subject.n_fenetres==0)
	    return;
	occupied=false;
	while(occupied_interrupt){
	    System.out.println("debut paint");
	}
	occupied=false;
	if(grph==null)
	    return;
	peignant=true;
	effacer();
	if(debut_event)
	    debut_event=false;
	grph.setColor(Color.blue);
	//System.out.println("avant appariement n_particules_initiales "+n_particules_initiales+" n_particules_finales "+n_particules_finales);
	for(int j=0;j<n_particules_finales;j++){
	    //System.out.println(" j "+j);
	    part_comp_finale[j].pos_part_int.assigne(part_comp_finale[j].pos_part);
	    part_comp_finale[j].dessine_part(true);  
	    part_comp_finale[j].nouvelle=false;   
	}
	if(i_demarre==0||i_demarre==1){
	    x_du_cdm+=v_du_cdm.x*nb_dt_sans_paint*dt_fenetre;
	    //v_du_cdm.print(" x_du_cdm "+x_du_cdm+" v_du_cdm ");
	}
	for(int j=0;j<n_particules_initiales;j++)
	    part_comp[j].calcule_coordonnes_int();
	for(int j=0;j<n_particules_initiales;j++)
	    part_comp[j].dessine_puits_ou_quantique();
	for(int j=0;j<n_particules_initiales;j++)
	    part_comp[j].dessine_part(false);
	//if(i_demarre==0)
	//part_comp[871]=null;
	if(du_nouveau){
	    if(indice_pedago==1)
		resume_int.dessine_resume_intermediaire(0);
	    resume_int.dessine_resume_intermediaire(indice_pedago);
	    
	    
	    //if(!relire_l_evenement&&i_demarre==2)
	    if(!relire_l_evenement&&i_demarre==2&&parite_pedago)//!!
		ecrire_evt_prec(-500,point_nul_int);
	    n_quarks_pedago=0;
	    for(int j=0;j<n_particules_initiales;j++)		
		if(part_comp[j].a_ete_dessinee){
		    part_comp[j].r_precedent.assigne_int(part_comp[j].r_dernier);
		    part_comp[j].a_ete_dessinee=false;
		}
	}else if(i_demarre==2)
	    ecrire_evt_prec(-100,point_nul_int);
	if(subject.comm!=null&&!image_h_deja_la){
	    subject.comm.setVisible(true);image_h_deja_la=true;
	    if(i_demarre==2)
		//subject.comm.grp_c.drawImage(subject.image_h,20,30,null);
		subject.ecriture_aide(2);
	}
	if(du_nouveau)
	    if(indice_pedago<nb_images_pedago-1){
		if(!parite_pedago)
		    indice_pedago++;
		parite_pedago=!parite_pedago;
	    }
	peignant=false;
    }
    void verifie_parite_couleurs(int nboucles){
	int par_couleur[]=new int[3];
	int par_q[]=new int[2];
	int par_couleur_sans[]=new int[3];
	int par_q_sans[]=new int[2];
	for (int j=0;j<3;j++){
	    par_couleur_sans[j]=0;
	    par_couleur[j]=0;
	}
	for (int j=0;j<2;j++){
	    par_q_sans[j]=0;
	    par_q[j]=0;
	}
	for(int j=0;j<n_particules_initiales;j++){
	    par_couleur[part_comp[j].quark[0].num_couleur]++;
	    if(part_comp[j].quark[0].anti_q)
		par_q[1]++;
	    else
		par_q[0]++;
	    if(part_comp[j].quark[0].partenaire==-1){
		par_couleur_sans[part_comp[j].quark[0].num_couleur]++;
		if(part_comp[j].quark[0].anti_q)
		    par_q_sans[1]++;
		else
		    par_q_sans[0]++;
	    }
	}
	if(nboucles==-5)
	    System.out.println(" n_particules_initiales "+n_particules_initiales+" par_q[0] "+par_q[0]+" par_q[1] "+par_q[1]+" par_couleur[0] "+par_couleur[0]+" par_couleur[1] "+par_couleur[1]+" par_couleur[2] "+par_couleur[2]);
	for (int j=0;j<3;j++)
	    if(par_couleur[j]/2*2!=par_couleur[j]||j==0&&par_q[0]!=par_q[1]){
		System.out.println(" nboucles "+nboucles+" par_q[0] "+par_q[0]+" par_q[1] "+par_q[1]+" par_q_sans[0] "+par_q_sans[0]+" par_q_sans[1] "+par_q_sans[1] );	
		System.out.println(" par_couleur[0] "+par_couleur[0]+" par_couleur[1] "+par_couleur[1]+" par_couleur[2] "+par_couleur[2]+" par_couleur_sans[0] "+par_couleur_sans[0]+" par_couleur_sans[1] "+par_couleur_sans[1]+" par_couleur_sans[2] "+par_couleur_sans[2] );
		if(nboucles>=0)
		    for(int i=0;i<n_particules_initiales;i++)
			System.out.println(" i "+i+" num_couleur "+part_comp[i].quark[0].num_couleur+" anti_q "+part_comp[i].quark[0].anti_q+" partenaire "+part_comp[i].quark[0].partenaire);
		par_couleur[10]=0;
	    }
    }
    void couplages(){
	//System.out.println("debut couplages ");
        boolean couplage_fait=false;
	for(int j=0;j<n_particules_initiales;j++){
	    part_comp[j].quark[0].partenaire=-1;
	    part_comp[j].quark[0].distance_car=100000000.;
	    part_comp[j].quark[0].hadronise=false;
	}
	//System.out.println("avant appariement n_particules_initiales "+n_particules_initiales+" n_particules_finales "+n_particules_finales);
	//verifie_parite_couleurs(-1);
	for(int j=0;j<n_particules_initiales-1;j++){
	    if(part_comp[j].quark[0].partenaire==-1){
		compere[j]=le_plus_proche(j);
		if(compere[j]!=-1){
		    part_comp[j].quark[0].partenaire=compere[j];		    
		    part_comp[compere[j]].quark[0].partenaire=j;
		}
	    }
	}
    }
    void hadronisation(){
	//hadronisation en baryons.
	baryon_cree=false;
	if(n_particules_initiales>=6&&Math.random()>0.05){
	    for(int i=0;i<n_particules_initiales;i++){
		liste_des_elimines[0]=i;
		liste_des_elimines[1]=part_comp[i].quark[0].partenaire;
		if(part_comp[i].nouvelle||part_comp[liste_des_elimines[1]].nouvelle)
		    break;
		for(int j=i+1;j<n_particules_initiales;j++){
		    liste_des_elimines[2]=j;
		    if(part_comp[j].quark[0].anti_q==part_comp[i].quark[0].anti_q&&part_comp[j].quark[0].num_couleur!=part_comp[i].quark[0].num_couleur){
			toto.assigne_soustrait(part_comp[i].pos_part,part_comp[j].pos_part);
			if(toto.longueur_carre()<dist2_baryon){
			    liste_des_elimines[3]=part_comp[j].quark[0].partenaire;	
			    if(part_comp[j].nouvelle||part_comp[liste_des_elimines[3]].nouvelle)
				break;
			    toto.assigne_soustrait(part_comp[liste_des_elimines[1]].pos_part,part_comp[liste_des_elimines[3]].pos_part);
			    if(toto.longueur_carre()<dist2_baryon){
				for(int k=j+1;k<n_particules_initiales;k++){
				    liste_des_elimines[4]=k;
				    if(part_comp[k].quark[0].anti_q==part_comp[j].quark[0].anti_q&&part_comp[k].quark[0].num_couleur!=part_comp[i].quark[0].num_couleur&&part_comp[k].quark[0].num_couleur!=part_comp[j].quark[0].num_couleur){
					toto.assigne_soustrait(part_comp[k].pos_part,part_comp[i].pos_part);
					if(toto.longueur_carre()<dist2_baryon){
					    toto.assigne_soustrait(part_comp[k].pos_part,part_comp[j].pos_part);
					    if(toto.longueur_carre()<dist2_baryon){
						liste_des_elimines[5]=part_comp[k].quark[0].partenaire;
						if(part_comp[k].nouvelle||part_comp[liste_des_elimines[4]].nouvelle)
						    break;
						toto.assigne_soustrait(part_comp[liste_des_elimines[4]].pos_part,part_comp[liste_des_elimines[1]].pos_part);
						if(toto.longueur_carre()<dist2_baryon){
						    toto.assigne_soustrait(part_comp[liste_des_elimines[4]].pos_part,part_comp[liste_des_elimines[3]].pos_part);
						    if(toto.longueur_carre()<dist2_baryon){
							cree_paire_baryons();
							break;							
						    }
						}
					    }
					}
				    }
				}
			    }
			}
		    }
		    if(baryon_cree)
			break;	    		    
		}
		if(baryon_cree)
		    break;	    
	    }
	}
	if(baryon_cree){
	    verifie_parite_couleurs(-2);
	    eliminer_quarks_hadronises();
	    if(n_mesons+n_baryons>27)
		evenement_termine_exterieurement=true;
	    verifie_parite_couleurs(-3);
	}
	//hadronisation en mesons.
	
	boolean meson_cree=false;
	if(!baryon_cree&&(cdm&&Math.random()>0.6||!cdm&&Math.random()>0.35)){
	    for(int j=0;j<n_particules_initiales;j++){
		int i=part_comp[j].quark[0].partenaire;
		if(part_comp[i].nouvelle||part_comp[j].nouvelle||(n_particules_initiales==2&&(part_comp[i].initiale||part_comp[j].initiale)))
		    break;
		if(j<i&&!part_comp[i].quark[0].hadronise&&!part_comp[j].quark[0].hadronise){
		    //if(cdm)
		    //part_comp[872]=null;
		    toto.assigne_soustrait(part_comp[i].pos_part,part_comp[j].pos_part);
		    //System.out.println(" i "+i+" j "+j+" toto.longueur_carre() "+(float)toto.longueur_carre());
		    if(masse_invariante(part_comp[i],part_comp[j])<inv_mass_ref&&toto.longueur_carre()<dist2_hadronisation||n_essais_paires_2_quarks_restant>10||(n_particules_initiales==2&&!(part_comp[i].initiale||part_comp[j].initiale))){

			cree_meson(i,j);
			//part_comp[871]=null;
			meson_cree=true;
			break;
		    }
		}
	    }
	}
	if(meson_cree){
	    verifie_parite_couleurs(-4);
	    eliminer_quarks_hadronises();
	    if(n_mesons+n_baryons>27)
		evenement_termine_exterieurement=true;
	    verifie_parite_couleurs(-5);
	}
	//System.out.println("avant hadr. n_particules_initiales "+n_particules_initiales+" n_particules_finales "+n_particules_finales);
	//System.out.println("n_particules_initiales "+n_particules_initiales+" n_particules_finales "+n_particules_finales);
    }
    void eliminer_quarks_hadronises(){
	int j=n_particules_initiales;
	while(j>0){
	    j--;
	    if(part_comp[j].quark[0].hadronise){
		//System.out.println(" quark "+j+" hadronise");
		for(int k=j;k<n_particules_initiales-1;k++){
		   for(int m=k+1;m<n_particules_initiales;m++)
		       if(part_comp[m].particule_mere>=k+1){
			   System.out.println(" k "+k+" m "+m+"  part_comp[m].particule_mere "+ part_comp[m].particule_mere);
			   part_comp[m].particule_mere--;
		       }  
		   for(int m=0;m<n_particules_initiales;m++)
		       if(m!=k&&part_comp[m].quark[0].partenaire>=k+1)
			   part_comp[m].quark[0].partenaire--;  

		    part_comp[k]=part_comp[k+1];
		    part_comp[k].num_part=k;
		    //System.out.println(" k "+k); 
		}
		n_particules_initiales--;
	    }
	}
    }
    void cree_baryon(int ind){
	int i=liste_des_elimines[ind];int j=liste_des_elimines[ind+2];int k=liste_des_elimines[ind+4];
	toto.assigne_additionne(part_comp[i].pos_part,part_comp[j].pos_part);
	toto.additionne(part_comp[k].pos_part);
	toto.multiplie_cst(1./3.);
	toto_int.assigne(toto);
	vatf.assigne_additionne(part_comp[i].vit_part,part_comp[j].vit_part);
	vatf.additionne(part_comp[k].vit_part);
	vatf.multiplie_cst(m_quarks/m_baryon);
	part_comp_finale[n_particules_finales]=new particule_finale(toto_int,diametre_baryon,vatf,"baryon",n_particules_finales,i,j,k);
	n_particules_finales++;
    }
    void cree_paire_baryons(){
	quadrimoment_tot_quarks_hadrons("avant creation baryons",true,false,false);
	cree_baryon(0);
	cree_baryon(1);
	for(int j=0;j<6;j++)
	    part_comp[liste_des_elimines[j]].quark[0].hadronise=true;
	quadrimoment_tot_quarks_hadrons("milieu creation baryon",true,false,false);
	double c=-(quadrimoment_tot_init.energie-quad_quarks_hadrons.energie);
	n_baryons+=2;	solu_impossible[0]=false;solu_impossible[3]=false;
	cherche_une_solution(c,6);
	//System.out.println(" solu_impossible[1] "+solu_impossible[1]);
	if(!solu_impossible[0]&&!solu_impossible[3]){
	    du_nouveau=true;
	    resume_int.incremente("Une paire de baryons a été créée  ",indice_pedago);
	    baryon_cree=true;
	    for(int j=0;j<6;j++){
		int i=liste_des_elimines[j];
		part_comp[i].quark[0].dessine_q(false,part_comp[i].quark[0].num_couleur,toto_int,false);
	    }
	}else{
	    n_particules_finales-=2;
	    n_baryons-=2;
	    for(int j=0;j<6;j++)
		part_comp[liste_des_elimines[j]].quark[0].hadronise=false;
	    n_refus_baryon++;
	}
	//System.out.println(" n_baryons "+n_baryons+" n_mesons "+n_mesons);
	quadrimoment_tot_quarks_hadrons("fin creation baryon",true,false,false);
    }
    void cree_meson(int i,int j){
	//System.out.println("creation du meson "+n_particules_finales+" à partir des quarks i "+i+" et j "+j);
	//System.out.println(" part_comp[i].particule_mere "+ part_comp[i].particule_mere+" part_comp[j].particule_mere "+ part_comp[j].particule_mere);
	quadrimoment_tot_quarks_hadrons("avant creation meson",true,false,false);
	if(part_comp[i].quark[0].anti_q==part_comp[j].quark[0].anti_q){
	    //System.out.println("part_comp[i].quark[0].num_couleur "+part_comp[i].quark[0].num_couleur+"part_comp[j].quark[0].num_couleur "+part_comp[j].quark[0].num_couleur);
	    //System.out.println("part_comp[i].quark[0].anti_q "+part_comp[i].quark[0].anti_q+"part_comp[j].quark[0].anti_q "+part_comp[j].quark[0].anti_q);
	    part_comp[1234]=null;
	}
	toto.assigne_additionne(part_comp[i].pos_part,part_comp[j].pos_part);
	toto.multiplie_cst(0.5);
	toto_int.assigne(toto);
	if(cdm){
	    toto.assigne_soustrait(part_comp[i].pos_part,part_comp[j].pos_part);
	    if(Math.abs(toto.x)>80.&&Math.abs(toto.y)<10)
		if(toto_int.y<ordonnee_initiale)
		    toto_int.y-=20;
		else
		    toto_int.y+=2;
	}
	vatf.assigne_additionne(part_comp[i].vit_part,part_comp[j].vit_part);
	vatf.multiplie_cst(m_quarks/m_pi_meson);  
	part_comp_finale[n_particules_finales]=new particule_finale(toto_int,diametre_meson,vatf,"meson",n_particules_finales,i,j,-1);
	n_particules_finales++;
	part_comp[i].quark[0].hadronise=true;
	part_comp[j].quark[0].hadronise=true;
	quadrimoment_tot_quarks_hadrons("milieu creation meson",true,false,false);
	//quad_quarks_hadrons_prec.assigne(quad_quarks_hadrons);
	double c=-(quadrimoment_tot_init.energie-quad_quarks_hadrons.energie);
	liste_des_elimines[0]=i;liste_des_elimines[1]=j;
	n_mesons++;
	solu_impossible[2]=false;solu_impossible[3]=false;
	cherche_une_solution(c,2);
	//System.out.println(" solu_impossible[1] "+solu_impossible[1]);
	if(!solu_impossible[2]&&!solu_impossible[3]){
	    part_comp[i].quark[0].hadronise=true;
	    part_comp[j].quark[0].hadronise=true;
	    du_nouveau=true; 
	    resume_int.incremente(" Un meson a ete cree ",indice_pedago);
  
	    part_comp[i].quark[0].dessine_q(false,part_comp[i].quark[0].num_couleur,toto_int,false);
	    part_comp[j].quark[0].dessine_q(false,part_comp[j].quark[0].num_couleur,toto_int,false);
	    quadrimoment_tot_quarks_hadrons("meson cree ",true,false,false);
	    //part_comp[867]=null;
	}else{
	    quadrimoment_tot_quarks_hadrons("meson pas cree ",true,false,false);
	    debuter_new_event=true;
	    //part_comp[868]=null;
	    n_particules_finales--;	    n_mesons--;
	    part_comp[i].quark[0].hadronise=false;
	    part_comp[j].quark[0].hadronise=false;

	    System.out.println(" impossibilité de creer ce meson ");
	}
	quadrimoment_tot_quarks_hadrons("fin creation meson",true,false,false);
	//part_comp[870]=null;
    }
    double dist2_min_d_un_quark_exterieur(int i,int j){
	double da=10000000.;
	double d=0;
	if(part_comp[i].quark[0].initial&&part_comp[j].quark[0].initial&&n_particules_finales==0)
	    return d;
	for(int jj=0;jj<n_particules_initiales;jj++){
	    if(i!=jj&&j!=jj){
		vatf.assigne_soustrait(part_comp[i].pos_part,part_comp[jj].pos_part);
		d=vatf.longueur_carre();
		if(d<=da)
		    da=d;
		//System.out.println(" j "+j+" jj "+jj+" d "+(float)d+" da "+(float)da);
		vatf.assigne_soustrait(part_comp[j].pos_part,part_comp[jj].pos_part);
		d=vatf.longueur_carre();
		if(d<=da)
		    da=d;
		//System.out.println(" j "+j+" jj "+jj+" d "+(float)d+" da "+(float)da);
	    }
	}
	return da;
    }
    int le_plus_proche(int j){
	int jjmin=-1;
	double da=10000000.;
	int npas=0;
	for(int jj=j+1;jj<n_particules_initiales;jj++){
	    if(j!=jj)
		if((part_comp[j].quark[0].num_couleur==part_comp[jj].quark[0].num_couleur)&&(part_comp[j].quark[0].anti_q!=part_comp[jj].quark[0].anti_q)&&part_comp[jj].quark[0].partenaire==-1){
		    vatf.assigne_soustrait(part_comp[j].pos_part,part_comp[jj].pos_part);
		    double d=vatf.longueur_carre();
		    npas++;
		    //System.out.println("j "+j+" jj "+jj+" d "+(float)d+" da "+(float)da);
		    if(d<=da){
			da=d;
			jjmin=jj;
			//System.out.println("j "+j+"jjmin "+jjmin);
		    }
		}
	}
	return jjmin;
    }
    void sauve_vitesses_particules(){
	for(int j=0;j<n_particules_initiales;j++){
	    part_comp[j].vit_part_sauv.assigne(part_comp[j].vit_part);
	    //part_comp[j].vit_part_sauv.print("j "+j+" part_comp[j].vit_part_sauv ");
	}
	for(int j=0;j<n_particules_finales;j++)
	    part_comp_finale[j].vit_part_sauv.assigne(part_comp_finale[j].vit_part);
    }
    void restaure_vitesses_particules(){
	for(int j=0;j<n_particules_initiales;j++){
	    part_comp[j].vit_part.assigne(part_comp[j].vit_part_sauv);
	    //part_comp[j].vit_part.print("j "+j+" part_comp[j].vit_part ");
	}
	for(int j=0;j<n_particules_finales;j++)
	    part_comp_finale[j].vit_part.assigne(part_comp_finale[j].vit_part_sauv);
    }
    public void gluon_avec_creation_paire(int part1,int part2){
	if(part_comp[part1].quark[0].anti_q==part_comp[part2].quark[0].anti_q)
	    return;
	if(n_particules_initiales>=38)return;
	//if(nb_de_coups_pour_rien>3)
	//  System.out.println(" entree gluon avec");
	point disstt=new point(part_comp[part1].pos_part);disstt.soustrait(part_comp[part2].pos_part);
	if(disstt.longueur_carre()<dist2_min_creation_paire){
	    //System.out.println(" part1 "+part1+" part2 "+part2+" part_comp[part1].quark[0]_couleur "+part_comp[part1].quark[0].num_couleur);
	    //dessine_gluon_lie(part_comp[part1].quark[0],part_comp[part2].quark[0]);
	    return;
	}
	//if(nb_de_coups_pour_rien>3)
	//  System.out.println(" apres critere dist_lim_pot ");
	double inv_mass=masse_invariante(part_comp[part1],part_comp[part2]);
	double fact=2.;
	//if(qu2.farbe.col!=qu1.farbe.col)
	//    fact=2.;
	//else
	//    fact=4.;
	//if(inv_mass>inv_mass_ref/fact&&disstt.longueur_carre()>dist2_min_creation_paire&&(part_comp[part1].vit_part.longueur_carre()>vit_part_mini*vit_part_mini||part_comp[part2].vit_part.longueur_carre()>vit_part_mini*vit_part_mini)&&Math.random()>0.1){
	//if(inv_mass>inv_mass_ref/fact){
	
	if(cdm&&Math.random()>0.6||!cdm&&Math.random()>0.9){
	    quadrimoment_tot_quarks_hadrons("avant creation d'une paire",true,false,false);
	    quad_quarks_seuls_prec.assigne(quad_quarks_seuls);
	    sauve_vitesses_particules();
	    point posit_point_creation=new point(0,0);
	    double bbb=0.4*Math.random();
	    int iqu=0,ppaarrtt=0,p_spec=0;
	    if(part_comp[part2].vit_part.longueur_carre()>part_comp[part1].vit_part.longueur_carre()){
		ppaarrtt=part2;p_spec=part1;iqu=2;
	    }else{
		ppaarrtt=part1;p_spec=part2;iqu=1;
	    }
	    //System.out.println("ppaarrtt "+ppaarrtt+" num_couleur "+part_comp[ppaarrtt].quark[0].num_couleur+" anti_q "+part_comp[ppaarrtt].quark[0].anti_q);
	    couleur_sauv=part_comp[ppaarrtt].quark[0].num_couleur;
	    //speed_q0.assigne_facteur(part_comp[ppaarrtt].vit_part,0.8*bbb);
	    //speed_q1.assigne_facteur(part_comp[ppaarrtt].vit_part,1.2*bbb);
	    speed_q0.assigne_soustrait(part_comp[ppaarrtt].vit_part,part_comp[p_spec].vit_part);
	    //speed_q0.assigne(part_comp[ppaarrtt].vit_part);
	    speed_q1.assigne(speed_q0);
	    speed_q0.multiplie_cst(0.8*bbb);
	    speed_q1.multiplie_cst(1.2*bbb);
	    speed_q0.rotation(-0.4+0.8*Math.random());
	    speed_q1.rotation(-0.4+0.8*Math.random());
	    part_comp[ppaarrtt].vit_part.soustrait(speed_q0);
	    part_comp[ppaarrtt].vit_part.soustrait(speed_q1);
	    posit_point_creation.assigne(part_comp[ppaarrtt].pos_part);
	    double len=speed_q0.longueur();
	    if(len>1.e-15)
		toto.assigne_facteur(speed_q0,20.*Math.random()/len);
	    else
		toto.assigne(point_nul);
	    posit_point_creation.additionne(toto);
	    point_int pppi=new point_int(posit_point_creation.x,posit_point_creation.y);
	    point_int pppf=new point_int(posit_point_creation.x,posit_point_creation.y);
	    point_int r_int=new point_int(posit_point_creation);
	    part_comp[ppaarrtt].a_deja_cree=true;
	    part_comp[n_particules_initiales]=new particule_initiale(r_int,diametre2,speed_q0,"quark",n_particules_initiales,ppaarrtt);
	    part_comp[n_particules_initiales].pos_part.print("n_particules_initiales"+n_particules_initiales +"part_comp[n_particules_initiales]");
	    n_particules_initiales++;
	    part_comp[n_particules_initiales]=new particule_initiale(r_int,diametre2,speed_q1,"antiquark",n_particules_initiales,ppaarrtt);
	    part_comp[n_particules_initiales].pos_part.print("n_particules_initiales"+n_particules_initiales +"part_comp[n_particules_initiales]");
	    n_particules_initiales++;
	    int numco=part_comp[ppaarrtt].quark[0].num_couleur;
	    part_comp[ppaarrtt].quark[0].num_couleur=decide_couleurs(part_comp[ppaarrtt].quark[0]);
	    boolean du_nouveau_prec=du_nouveau;
	    du_nouveau=true;
	    resume_int.incremente(" Une paire de quarks a été créée ",indice_pedago);
	    if(part_comp[ppaarrtt].quark[0].num_couleur!=numco){
		/*
		n_particules_initiales-=2;	
		quadrimoment_tot_quarks_hadrons("changement de couleur!",true,false,true);
		n_particules_initiales+=2;
		*/
		part_comp[ppaarrtt].quark[0].chgt_coul=true;
		//part_comp[ppaarrtt].nouvelle=true;
		part_comp[ppaarrtt].particule_mere=-1;
		System.out.println(" ùùùùùppaarrtt "+ppaarrtt+" numco "+numco+" part_comp[ppaarrtt].quark[0].num_couleur "+part_comp[ppaarrtt].quark[0].num_couleur);
	    }
	    quadrimoment_tot_quarks_hadrons("milieu creation paires",true,false,false);
	    int nn=n_particules_initiales-2;
	    if(nn>=0){
		double a=6/m_quarks;
		double b=part_comp[nn+1].vit_part.somme_comp()+part_comp[nn].vit_part.somme_comp();
		b-=2*part_comp[ppaarrtt].vit_part.somme_comp();
		double c=-(quadrimoment_tot_init.energie-quad_quarks_hadrons.energie);
		double delta=b*b-4*a*c;
		System.out.println("ùùùùùùùdelta "+(float)delta+" c "+(float)c+" a "+(float)a+" b "+(float)b);
		if(delta<0.){
		    if(!cherche_une_solution(c,0)){
			n_particules_initiales-=2;
			n_refus_paires++;
			part_comp[ppaarrtt].quark[0].chgt_coul=false;
			part_comp[ppaarrtt].nouvelle=false;
			du_nouveau=du_nouveau_prec;
			restaure_vitesses_particules();
			part_comp[ppaarrtt].quark[0].num_couleur=couleur_sauv;
			System.out.println(" pas de solution trouvee, on restaure ");
			quadrimoment_tot_quarks_hadrons("",true,false,false);
			quad_quarks_hadrons.print(" pas de solution trouvee quad_quarks_seuls. ");
			quad_quarks_seuls.print(" quad_quarks_seuls. ");
		    }
		}else{
		    solu_impossible[5]=solution_minimale_sec_deg(a,b,c);
		    part_comp[nn+1].vit_part.additionne_diviseur(dp_p_sec_deg,m_quarks);
		    part_comp[nn].vit_part.additionne_diviseur(dp_p_sec_deg,m_quarks);	    
		    part_comp[ppaarrtt].vit_part.additionne_facteur(dp_p_sec_deg,-2/m_quarks);
		    quadrimoment_tot_quarks_hadrons("solution trouvee directe",true,false,false);
		    quad_quarks_seuls.print(" solution trouvee quad_quarks_seuls.");
		    /*
		    c=-(quad_quarks_seuls_prec.energie-quad_quarks_seuls.energie);
		    quad_quarks_seuls.print("avant rebelote"+" c "+c+" solution trouvee quad_quarks_seuls.");
		    solu_trouvee=cherche_une_solution(c);
		    */
		}
		quadrimoment_tot_quarks_hadrons("fin creation paires",false,false,false);
		double cc=-(quadrimoment_tot_init.energie-quad_quarks_hadrons.energie);
		if(Math.abs(cc)>10.){
		    quadrimoment_tot_init.print(" echec creation paires  quadrimoment_tot_init ");
		    debuter_new_event=true;
		    //part_comp[202]=null;
		}
		//verifie_parite_couleurs(0);
		a_cree_une_paire=true;
		draw_line_anti(pppi,pppf,coul[part_comp[n_particules_initiales-1].quark[0].num_couleur].col,coul[part_comp[n_particules_initiales-2].quark[0].num_couleur].col,grph,0,part_comp[n_particules_initiales-1].quark[0].diam_quark,7);
	    }
	    return;
	}
    }  
    boolean cherche_une_solution(double c,int nb_eli){
	solu_impossible[6]=false;
	if(n_particules_finales!=0){
	    if(n_particules_initiales-nb_eli==0){
		compense_quadrimoment_par_hadrons_seuls(n_particules_finales,c,3);
		if(!solu_impossible[3])
		    return true;
	    }else{
		compense_quadrimoment_par_quarks_seuls(c,2,nb_eli);
		if(solu_impossible[2]){
		    if(n_particules_initiales-nb_eli>0){
			compense_par_quarks_et_hadrons(n_particules_initiales-nb_eli,n_particules_finales,c,6,liste_des_elimines,nb_eli);
			if(!solu_impossible[6])
			    return true;
		    }
		    return false;
		}else{
		    return true;
		}
	    }
	}else{
	    compense_quadrimoment_par_quarks_seuls(c,2,nb_eli);
	    return !solu_impossible[2];
	}
	return false;
    }
    void verifie_couleurs(int num_in,int part1,int iqu){
	int num1=part_comp[part1].quark[0].num_couleur;
	int num2=part_comp[n_particules_initiales-1].quark[0].num_couleur;
	int num3=part_comp[n_particules_initiales-2].quark[0].num_couleur;
	if(num1==num2&&num3!=num_in||num1==num3&&num2!=num_in||num3==num2&&num1!=num_in){
	    System.out.println("iqu "+iqu+" part1 "+part1+" num1 "+ num1+" num2 "+ num2+" num3 "+ num3);
	    part_comp[part1].quark[30]=null;
	}
    }
    double masse_invariante(particule_initiale p1,particule_initiale p2){
	return p1.vit_part.longueur()*p2.vit_part.longueur()-p1.vit_part.scalaire(p2.vit_part);
    }
    public int decide_couleurs(particule_initiale.quark qu){
	double ddd=3.*Math.random();
	int c1=-1;
	int nn_cc=-1;
	//couleur cool1;
	if(ddd>2.){
	    //cool1=new couleur(0,0,0);
	    nn_cc=-1;
	    part_comp[n_particules_initiales-1].quark[0].num_couleur=qu.num_couleur;
	    part_comp[n_particules_initiales-2].quark[0].num_couleur=qu.num_couleur;
	    c1=qu.num_couleur;
	}else{
	    if(ddd>1.)
		nn_cc=coul[qu.num_couleur].n_c_cpl1;
		//cool1=new couleur(qu.farbe.r_cpl1,qu.farbe.v_cpl1,qu.farbe.b_cpl1);
	    else
		nn_cc=coul[qu.num_couleur].n_c_cpl2;
		//cool1=new couleur(qu.farbe.r_cpl2,qu.farbe.v_cpl2,qu.farbe.b_cpl2);
	    if(qu.anti_q!=part_comp[n_particules_initiales-1].quark[0].anti_q){
		part_comp[n_particules_initiales-1].quark[0].num_couleur=nn_cc;
		part_comp[n_particules_initiales-2].quark[0].num_couleur=qu.num_couleur;
		//cl.assigne(cool1);
		c1=nn_cc;
	    }else{
		//part_comp[n_particules_initiales-2].quark[0].farbe.assigne(cool1);
		//part_comp[n_particules_initiales-1].quark[0].farbe.assigne(qu.farbe);
		//cl.assigne(cool1);
		part_comp[n_particules_initiales-2].quark[0].num_couleur=nn_cc;
		part_comp[n_particules_initiales-1].quark[0].num_couleur=qu.num_couleur;
		c1=nn_cc;
	    }
	}
	//if((cl.r==255)&&(cl.r==255)&&(cl.b==255))
	System.out.println("ddd "+ddd+" nn_cc "+nn_cc+" qu.num_couleur "+qu.num_couleur+" coul[qu.num_couleur].n_c_cpl1 "+coul[qu.num_couleur].n_c_cpl1+" coul[qu.num_couleur].n_c_cpl2 "+coul[qu.num_couleur].n_c_cpl2+" c1 "+c1);
	return c1;
    }
    public void dessine_gluon_lie(particule_initiale.quark qu1,particule_initiale.quark qu2, boolean nouveau,boolean enregistrer){
	//System.out.println("entree occupied "+occupied);
	if(ne_voir_que_les_gluons_reels||faire_stat)
	    return;
	boolean aa=true;double bb=0.;
	grph.setColor(coul[qu1.num_couleur].col);
	//qu1.force.print("qu1.force ");qu2.force.print("qu2.force ");
	if(quantique&&!enregistrer){
	    bb=Math.random();
	    aa=(bb<0.1);
	    //		if(i_print<100)System.out.println(" ia "+ia+" bb "+bb);
	}
	if(aa){
	    nb_gluons_event++;
	    int ia=(int)(bb*100.);
	    draw_line_anti(qu1.posit_q_int,qu2.posit_q_int,coul[qu1.num_couleur].col_pale,coul[qu2.num_couleur].col_pale,grph,ia,qu1.diam_quark,5);
	    if(i_demarre==0||i_demarre==1)	    
		draw_line_anti(qu1.posit_q_int_labo,qu2.posit_q_int_labo,coul[qu1.num_couleur].col_pale,coul[qu2.num_couleur].col_pale,grph,ia,qu1.diam_quark,5);
	    if(i_demarre==2&&nb_gluons_event==1){
		point_int qq_1=new point_int(qu1.posit_q_int);
		qq_1.x*=fact_zoom;
		if(cdm)
		    qq_1.y-=(ordonnee_initiale-50);
		else//%%
		    //qq_1.y*=fact_zoom;//%%
		    qq_1.y=(int)(qq_1.y*fact_zoom-50);//%%
		point_int qq_2=new point_int(qu2.posit_q_int);
		qq_2.x*=fact_zoom;
		if(cdm)
		    qq_2.y-=(ordonnee_initiale-50);
		else//%%
		    //qq_2.y*=fact_zoom;//%%
		qq_2.y=(int)(qq_2.y*fact_zoom-50);//%%
		for(int i=indice_pedago;i<nb_images_pedago;i++)
		    draw_line_anti(qq_1,qq_2,coul[qu1.num_couleur].col_pale,coul[qu2.num_couleur].col_pale,resume_int.gTampon[i],ia,qu1.diam_quark/4,3);
	    }
	    if(enregistrer&&i_demarre==2){
		if(!qu1.anti_q)
		    ecrire_evt_prec((qu1.num_couleur+1)*10,qu1.posit_q_int);
		else
		    ecrire_evt_prec((-qu1.num_couleur-1)*10,qu1.posit_q_int);
		if(!qu2.anti_q)
		    ecrire_evt_prec((qu2.num_couleur+1)*10,qu2.posit_q_int);
		else
		    ecrire_evt_prec((-qu2.num_couleur-1)*10,qu2.posit_q_int);
	    }
	}
	//System.out.println("sortie occupied "+occupied);
    }
    public void draw_line_anti(point_int possi,point_int possf, Color couleur_1,Color couleur_2,Graphics gg,int ia,int diam_q,int i_epaisseur){	
	//posi.print("posi ");
	//	System.out.println(" antiq1, antiq2"+antiq1+antiq2);
	toto.assigne_int(possf);toto.soustrait_int(possi);
	fact_drwl=(diam_q/2.)/toto.longueur();
	posi_drwl.assigne_int(possi);
	posi_drwl.additionne_facteur(toto,fact_drwl);
	posf_drwl.assigne_int(possf);
	posf_drwl.additionne_facteur(toto,-fact_drwl);	
	if(!((posf_drwl.x==0)&&(posf_drwl.y==0))){
	    dessine_drwl=true; deja_dessine_drwl=0;
	    if(quantique)dessine_drwl=false;
	    if(posf_drwl.x>posi_drwl.x)
		ix_drwl=1;
	    else if(posf_drwl.x<posi_drwl.x)
		ix_drwl=-1;
	    if(posf_drwl.y>posi_drwl.y)
		iy_drwl=1;
	    else if(posf_drwl.y<posi_drwl.y)
		iy_drwl=-1;
	    if(posf_drwl.x!=posi_drwl.x)
		tg_drwl=(double)(posf_drwl.y-posi_drwl.y)/(double)(posf_drwl.x-posi_drwl.x);
	    else if(posf_drwl.y>posi_drwl.y)
		tg_drwl=1.57;
	    else
		tg_drwl=-1.57;
	    iss_drwl=1;
	    if(tg_drwl>0.)iss_drwl=-1;
	    if(Math.abs(tg_drwl)<1.)iss_drwl*=2;
	    if(Math.abs(tg_drwl)<.5)iss_drwl=(int)(iss_drwl*1.6);
	    //if(Math.abs(tg_drwl)<.2)iss_drwl*=2;
	    vatf.assigne_soustrait(posi_drwl,posf_drwl);
	    d_drwl=(float)vatf.x*(float)vatf.x+(float)vatf.y*(float)vatf.y;
	    d_drwl=(float)Math.sqrt(d_drwl);//vatf.longueur();
	    //vatf.print(" d_drwl "+d_drwl+" vatf");
	    dx=(double)(ix_drwl*8*Math.abs((float)vatf.x)/d_drwl);
	    dy=(double)(iy_drwl*8*Math.abs((float)vatf.y)/d_drwl);
	    //if(dx<0.0001)dx=0.0001*ix_drwl;if(dy<0.0001)dy=0.0001*iy_drwl;
	    vatf.assigne(dx,dy);
	    //vatf.print("ix_drwl"+ix_drwl+" iy_drwl "+iy_drwl+" dessine_drwl "+dessine_drwl+" vatf");
	    if(!(Math.abs(dx)<2.&&Math.abs(dy)<2.))
		for(int anti=-1;anti<2;anti+=2){
		    int i=1,ijk=0;
		    toto.assigne(posi_drwl);
		    continuez_drwl=true;
		    if(Math.abs(dx)<2.||Math.abs(dy)<2.){
			if(Math.abs(dx)<2.){
			    continuez_drwl=toto.y<posf_drwl.y&&iy_drwl>=0||toto.y>=posf_drwl.y&&iy_drwl==-1;
			}else if(Math.abs(dy)<2.){
			    continuez_drwl=toto.x<=posf_drwl.x&&ix_drwl>=0||toto.x>=posf_drwl.x&&ix_drwl==-1;
			}
		    }else{
			if(anti==1)
			    toto.y+=iss_drwl;
			else
			    toto.y-=iss_drwl;
			continuez_drwl=(toto.x<=posf_drwl.x&&ix_drwl==1||toto.x>=posf_drwl.x&&ix_drwl==-1)&&(toto.y<posf_drwl.y&&iy_drwl==1||toto.y>=posf_drwl.y&&iy_drwl==-1);
		    }
		    while(continuez_drwl){
			ijk++;
			if(i==1){
			    if(anti==-1)
				gg.setColor(couleur_1);
			    else
				gg.setColor(couleur_2);
			    i=-1;
			}else{
			    if(anti==1)
				gg.setColor(gris_pale);
			    else
				gg.setColor(Color.white);
			    i=1;
			}
			if(dessine_drwl){
			    deja_dessine_drwl++;
			    for(int j=0;j<i_epaisseur;j++){
				if((toto.x+dx>10)&&(toto.y+dy>10))
				    if(!(Math.abs(dx)<2.||Math.abs(dy)<2.))
					//	toto.print(" toto ");
					gg.drawLine((int)(toto.x-anti*j*iss_drwl),(int)(toto.y-anti*iss_drwl),(int)(toto.x+dx-anti*j*iss_drwl),(int)(toto.y+dy-anti*iss_drwl));
				    else if(Math.abs(dx)<2.)
					gg.drawLine((int)toto.x-j*anti,(int)toto.y,(int)(toto.x+dx)-j*anti,(int)(toto.y+dy));
				    else if(Math.abs(dy)<2.)
					gg.drawLine((int)toto.x,(int)toto.y-j*anti,(int)(toto.x+dx),(int)(toto.y+dy)-j*anti);
			    }
			}
			toto.additionne(vatf);
			if((quantique&&((ijk/2)>ia)||(!quantique)))dessine_drwl=true;
			if(quantique)dessine_drwl&=(deja_dessine_drwl<3);
			//if(i_print<100)System.out.println(" ijk "+ijk+" ia "+ia+" dessine_drwl "+dessine_drwl);
			//i_print++;
			if(Math.abs(dx)<2.||Math.abs(dy)<2.){
			    if(Math.abs(dx)<2.){
				continuez_drwl=toto.y<posf_drwl.y&&iy_drwl>=0||toto.y>=posf_drwl.y&&iy_drwl==-1;
			    }else if(Math.abs(dy)<2.){
				continuez_drwl=toto.x<=posf_drwl.x&&ix_drwl>=0||toto.x>=posf_drwl.x&&ix_drwl==-1;
			    }
			}else
			    continuez_drwl=(toto.x<=posf_drwl.x&&ix_drwl==1||toto.x>=posf_drwl.x&&ix_drwl==-1)&&(toto.y<posf_drwl.y&&iy_drwl==1||toto.y>=posf_drwl.y&&iy_drwl==-1);
		    }
		}
	}
    }
    void gerer_l_evenement(){
	if(voir_ce_qui_a_change){
	    cqach.ecr_comm(i_pt_orange);
	    voir_ce_qui_a_change=false;
	}
	if(this!=null){
	    if(relire_l_evenement){
		if(!fin_event_naturelle)
		    bouge_fenetre();
		else{
		    relire_l_evenement=false;
		    en_train_de_lire=false;
		    /*
		      cliquee=false;
		      while(!cliquee){
		      System.out.println(" ¨¨¨¨¨¨¨¨¨¨");
		      }
		    */
		}
	    }else if(!attendre_signal_new_event)
		if(!fin_event_naturelle&&!evenement_termine_exterieurement){
		    //System.out.println(" vers bouge_fenetre() evenement_termine_exterieurement"+evenement_termine_exterieurement);
		    bouge_fenetre();
		}
	}
	if(staccato)
	    if((du_nouveau||fin_event_naturelle)&&!attendre_signal_new_event){
		//System.out.println("mmmmmmmmmfin_event_naturelle "+fin_event_naturelle);
		grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
		grph.setColor(Color.red);
		if(!fin_event_naturelle)
		    grph.drawString(" Cliquez dans cette fenetre ou utilisez le menu Actions.",170, 100);
		else{
		    subject.eraserect(grph,100-30,0,100,1000);
		    grph.setColor(Color.red);
		    grph.drawString(" Fin d'evenement, utilisez le menu Actions.",170, 100);
		}
		grph.setFont(subject.times_gras_14);
	    }	
	if((fin_event_naturelle||evenement_termine_exterieurement)&&!attendre_signal_new_event){
	    attendre_signal_new_event=true ;
	    fin_event_naturelle=false;
	    grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
	    if(!staccato)
		if(!faire_stat){
		    grph.drawString("Pour continuer utilisez le menu Actions. ",100,100);
		    if(n_mesons+n_baryons!=0){
			grph.setColor(Color.blue);
			grph.drawString(""+n_mesons+" mesons "+n_baryons+" baryons ",300,130);
		    }
		}
	    /*else
		    grph.drawString("Pour arreter, presser la souris dans cette fenetre. ",100,100);
	    */
	    grph.setFont(subject.times_gras_14);
	    itpp1.setEnabled(true);
	    p_quark_miny=1000; p_quark_maxy=-1000;
	    System.out.println("ùùùùùùùùùùfin_event_naturelle "+fin_event_naturelle+" n_refus_baryon "+n_refus_baryon+" n_refus_paires "+n_refus_paires);
	    System.out.println("ùùùùùùùùùùfin_event_naturelle "+fin_event_naturelle+" declencher_nouvel_event "+declencher_nouvel_event+" debuter_new_event "+debuter_new_event);
	    quadrimoment_tot_quarks_hadrons("  ",true,false,false);
	    quad_quarks_hadrons.print("quad_quarks_hadrons");
	    quadrimoment_tot_init.print(" quadrimoment_tot_init ");
	    debuter_new_event=mode_continu;
	    System.out.println("kkkkkkkkkk fin_event_naturelle "+fin_event_naturelle+" declencher_nouvel_event "+declencher_nouvel_event+" debuter_new_event "+debuter_new_event);
	}	
	if(debuter_new_event||declencher_nouvel_event){
	    debuter_nouvel_event();
	    declencher_nouvel_event=false;
	    System.out.println(" $$$$$vers bouge_fenetre() ");
	    bouge_fenetre();
	}

    }

    public void bouge_fenetre(){
	if(!desactiver){
	if(occupied_interrupt){
	    System.out.println("deb bouge_fen occupied_interrupt");
	    return;
	}

        accepte_bouge=false;
	if(du_nouveau&&!debut_event&&n_particules_initiales>0){
	    quadrimoment_tot_quarks_hadrons("verif du nouveau",true,false,false);
	    toto.assigne_soustrait(quad_quarks_hadrons.moment,quadrimoment_tot_init.moment);
	    double de=Math.abs(quad_quarks_hadrons.energie-quadrimoment_tot_init.energie);
	    //de+=20;
	    if(toto.longueur_carre()>10*10||de>20.){ 
		quad_quarks_seuls.print(" quad_quarks_seuls ");
		quad_quarks_hadrons.print(" quad_quarks_hadrons ");
		quadrimoment_tot_init.print("cdm "+cdm+" quadrimoment_tot_init ");
		debuter_new_event=true;
		//	part_comp[201]=null;
	    }
	}
	//System.out.println("debut bouge_fenetre debut_event"+debut_event+" numero_evt "+numero_evt+" command "+command+" cliquee "+cliquee+" staccato "+staccato);
	//System.out.println("numero_evt  "+numero_evt+" pressee "+pressee+ "mode_continu "+mode_continu+"staccato "+staccato+" i_demarre "+i_demarre);
	if(staccato){
	    if(cliquee||!du_nouveau){		    
		accepte_bouge=true;cliquee=false;
	    }
	}else 
	    accepte_bouge=true;
	if(pressee){
	    if(faire_stat){
		faire_stat=false;
		statistique.dispose();
		statistique=null;
	        mb=new MenuBar();			    
		barre_des_menus();
		pack();
		grph=null;
		setSize(size_x,size_y);setLocation(loc_x,loc_y);
		grph=getGraphics();
		System.out.println("size_x "+size_x+" size_y "+size_y+"loc_x "+loc_x+" loc_y "+loc_y);
		command=""; mode_continu=false;
		debuter_new_event=true;
	    }
	    while(pressee&&i_demarre!=2){
		System.out.println(" ahahahah pressee ");//pour laisser le temps de changer	
		//grph.drawString(" ahahahah pressee ",150, 140);
	    }
	}
	pressee=false;relachee=false;
	//if(i_demarre==2)
	//part_comp[2001]=null;
	//System.out.println("accepte_bouge "+accepte_bouge+" cliquee "+cliquee+ "mode_continu "+mode_continu+" debut_event "+debut_event+" i_demarre "+i_demarre);
	accepte_bouge=accepte_bouge&&!declencher_nouvel_event;
	melange=false;
	if(accepte_bouge){
	    occupied=true;
	    du_nouveau=false;
	    if(relire_l_evenement){
		effacer();
		fin_provisoire=false;
		du_nouveau=false;
		parite_pedago=false;
		pointeur_debut=ilec_evt_prec;
		while(!fin_provisoire&&!du_nouveau){
		    fin_provisoire=lire_et_peindre_evt_prec();
		}
		debut_event=false;
	    }else{
		nb_dt_sans_paint=10;
		if(i_demarre==2)
		    nb_dt_sans_paint=1;
		if(faire_stat)
		    nb_dt_sans_paint=40;
		int n_in=n_particules_initiales,n_fin=n_particules_finales;
		//nb_dt_sans_paint=1;
		for (int jevt=0;jevt<nb_dt_sans_paint;jevt++){
		    if(diffusion)
			n_passages++;
		    /*
		      if(!quantique)
		      for(int j=0;j<n_particules_initiales;j++)
		      if(part_comp[j].n_quark>1)
		      part_comp[j].forces();
		    */
		    melange_p=melange;
		    if(i_demarre==2){
			quadrimoment_tot_quarks_hadrons(" temps incrémenté ",true,false,false);
			if(debut_event){
			    quadrimoment_tot_init.assigne(quad_quarks_seuls);
			    //quadrimoment_tot_init.print(" quadrimoment_tot_init ");
			    //part_comp[3000]=null;
			}
			if(!ne_pas_creer_de_paire_au_prochain_passage){
			    for(int j=0;j<n_particules_initiales;j++)
				part_comp[j].a_deja_cree=false;
			    a_cree_une_paire=false;
			    for(int j=0;j<n_particules_initiales;j++){
				for(int jj=j+1;jj<n_particules_initiales;jj++){
				    if(part_comp[j].initiale&&part_comp[jj].initiale)
					gluon_avec_creation_paire(0,1);
				    else
					if(!part_comp[j].a_deja_cree&&!part_comp[jj].a_deja_cree){
					    if(part_comp[j].quark[0].partenaire==jj&&part_comp[jj].quark[0].partenaire==j)
						gluon_avec_creation_paire(j,jj);  
					}
				}
			    }
			    ne_pas_creer_de_paire_au_prochain_passage=a_cree_une_paire;
			}else
			    ne_pas_creer_de_paire_au_prochain_passage=false;
		    }
		    //if(n_particules_initiales==0)
		    //	part_comp[5200]=null;
		    for(int j=0;j<n_particules_initiales;j++)
			part_comp[j].zero_forces();
		    melange=forces_part_comp_diff();
		    if(melange){
			melange_initial=true;
			if(i_demarre==2){
			    int n_antiq=0,n_q=0;
			    for(int j=0;j<n_particules_initiales;j++){
				if(part_comp[j].quark[0].anti_q)
				    n_antiq++;
				else
				    n_q++;
				part_comp[j].vit_part.additionne_facteur(part_comp[j].force_part,dt_fenetre/part_comp[j].masse);
				part_comp[j].pos_part.additionne_facteur(part_comp[j].vit_part,dt_fenetre);
				//part_comp[j].pos_part.additionne_facteur(part_comp[j].force_part,dt_fenetre*dt_fenetre/2.);
			    }
			    if(n_q!=n_antiq){
				for(int j=0;j<n_particules_initiales;j++)
				    System.out.println("j "+j+" part_comp[j].quark[0].anti_q "+part_comp[j].quark[0].anti_q+" part_comp[j].quark[0].num_couleur "+part_comp[j].quark[0].num_couleur);
				part_comp[852]=null;
			    }
			}
		    }
		    if(i_demarre!=2){
			for(int j=0;j<n_particules_initiales;j++)
			    part_comp[j].forces();
			for(int j=0;j<n_particules_initiales;j++)
			    part_comp[j].bouge();
		    }else{
			for(int j=0;j<n_particules_finales;j++)
			    part_comp_finale[j].bouge();
			couplages();
			hadronisation();
			occupied=false;
			while(occupied_interrupt){
			    System.out.println("apres hadronisation occupied_interrupt");
			}
			occupied=true;
			couplages();
			for(int j=0;j<n_particules_initiales;j++){
			    if(part_comp[j].quark[0].partenaire==-1){
				System.out.println("pas de partenaires trouves pour le j= "+j);
				//part_comp[1000].quark[0].partenaire=0;
			    }
			}
			if(n_in!=n_particules_initiales||n_fin!=n_particules_finales)
			    break;
		    }
		    //if((n_in!=n_particules_initiales||n_fin!=n_particules_finales))
		    //System.out.println("modifs n_particules_initiales "+n_particules_initiales+" n_particules_finales "+n_particules_finales);
		}
		/*
		if(i_demarre==0||i_demarre==1){
		    System.out.println(" part_comp[0].force_part.x "+(float)part_comp[0].force_part.x+" part_comp[1].force_part.x "+ (float)part_comp[1].force_part.x+" part_comp[0].d_pos_part.x "+(float)part_comp[0].d_pos_part.x+" part_comp[1].d_pos_part.x "+ (float)part_comp[1].d_pos_part.x);
		cucu=(part_comp[0].pos_part.x+part_comp[1].pos_part.x)/2;
		cece=(part_comp[0].vit_part.x+part_comp[1].vit_part.x)/2;
		System.out.println(" part_comp[0].pos_part.x "+(float)part_comp[0].pos_part.x+" part_comp[1].pos_part.x "+ (float)part_comp[1].pos_part.x+" cucu "+(float)cucu+" cece "+(float)cece);
		}
		*/
		plus_rien_a_voir=true;
		if(n_particules_initiales==0){
		    nb_evts_purs_hadrons++;
		    paint();
		}else{
		    if(i_demarre==0||i_demarre==1){
			plus_rien_a_voir=(part_comp[0].pos_part_int.x<0||part_comp[0].pos_part_int.x>size_x||part_comp[0].pos_part_int.y<0||part_comp[0].pos_part_int.y>size_y);
			plus_rien_a_voir=plus_rien_a_voir||(part_comp[1].pos_part_int.x<0||part_comp[1].pos_part_int.x>size_x||part_comp[1].pos_part_int.y<0||part_comp[1].pos_part_int.y>size_y);
		    }else{
			for(int j=0;j<n_particules_initiales;j++){
			    //plus_rien_a_voir=plus_rien_a_voir&&(part_comp[j].pos_part_int.x<left||part_comp[j].pos_part_int.x>right||part_comp[j].pos_part_int.y<top||part_comp[j].pos_part_int.y>bot);
			    plus_rien_a_voir=plus_rien_a_voir&&(part_comp[j].pos_part_int.x<0||part_comp[j].pos_part_int.x>size_x||part_comp[j].pos_part_int.y<0||part_comp[j].pos_part_int.y>size_y);
			}
			for(int j=0;j<n_particules_finales;j++)
			    plus_rien_a_voir=plus_rien_a_voir&&(part_comp_finale[j].pos_part_int.x<0||part_comp_finale[j].pos_part_int.x>size_x||part_comp_finale[j].pos_part_int.y<0||part_comp_finale[j].pos_part_int.y>size_y);
		    }
		    if(n_passages>20000)plus_rien_a_voir=true;
		    est_demarre=est_demarre&&!plus_rien_a_voir;
		}
		if(plus_rien_a_voir){
		    if(faire_stat)
			if(n_passages<20000){
			    System.out.println("vers dessine_graphe ");
			    statistique.dessine_graphe();
			}
		    if(nb_de_pas_a_la_fin>0||nb_de_pas_a_la_fin==0&&i_demarre<=1||staccato){
			recommencement=false;
			fin_event_naturelle=true;
			if(mode_continu)
			    debuter_new_event=true;
			itf1.setEnabled(true);
			System.out.println("plus_rien_a_voir fin_event_naturelle numero_evt"+ numero_evt);
			if(i_demarre==2&&!mode_continu)
			    ecrire_evt_prec(-10000,point_nul_int);
		    }else
			nb_de_pas_a_la_fin++;
		    
		    //for (int k=0;k<iecr_evt_prec;k++){
		    //    System.out.println("k "+k+"evenement_precedent[k] "+evenement_precedent[k]);
		    //}
		    //part_comp[1053]=null;	
		}else{
		    //System.out.println(" vers paint occupied "+occupied+" occupied_interrupt "+occupied_interrupt);
		    //System.out.println(" avant paint ");
		    quadrimoment_tot_quarks_hadrons("avant paint",true,false,false);
		    //quad_quarks_seuls_prec.energie=quad_quarks_seuls.energie;
		    quad_quarks_seuls_prec.energie=quad_quarks_seuls.energie;
		    
		    paint();
		    quadrimoment_tot_quarks_hadrons("apres paint",true,false,false);
		    double c=-(quadrimoment_tot_init.energie-quad_quarks_hadrons.energie);
		    if(i_demarre==2)
			cherche_une_solution(c,0);
		}
	    }
	    occupied=false;
	}
	if(melange&&(i_demarre==0||i_demarre==1)&&echange_quarks)
	    reassemblage_des_quarks();
	}
    }
    void reassemblage_des_quarks(){
	assemblage_choisi=-1;
	vatf.assigne_additionne(part_comp[0].pos_part,part_comp[1].pos_part);
	watf.assigne_soustrait(part_comp[0].pos_part,part_comp[1].pos_part);
	if(i_demarre==0){
	    //part_comp[0].pos_part.print("part_comp[0].pos_part ");
	    //part_comp[1].pos_part.print("part_comp[1].pos_part ");
	    double masse_des_quarks=0.;
	    for(int ij=0;ij<3;ij++)
		masse_des_quarks+=part_comp[0].quark[ij].masse;
	    distance_carre_totale=1.e8;
	    for(int m=0;m<4;m++){
		pos_had0[m].assigne(point_nul);
		pos_had1[m].assigne(point_nul);
		for(int ij=0;ij<3;ij++){
		    pos_had0[m].additionne_facteur(part_comp[num_had_init0[ij][m]].quark[ij].posit_q,part_comp[num_had_init0[ij][m]].quark[ij].masse/masse_des_quarks);
		    pos_had1[m].additionne_facteur(part_comp[num_had_init1[ij][m]].quark[ij].posit_q,part_comp[num_had_init1[ij][m]].quark[ij].masse/masse_des_quarks);
		}
		coco=0.;cici=0.;
		for(int ij=0;ij<3;ij++){
		    tutu.assigne_soustrait(part_comp[num_had_init0[ij][m]].quark[ij].posit_q,pos_had0[m]);
		    coco+=tutu.longueur_carre();
		    tutu.assigne_soustrait(part_comp[num_had_init1[ij][m]].quark[ij].posit_q,pos_had1[m]);
		    cici+=tutu.longueur_carre();
		}
		if(coco+cici<distance_carre_totale){
		    distance_carre_totale=coco+cici;
		    assemblage_choisi=m;
		    
		    //System.out.println("m "+m+" distance_carre_totale "+distance_carre_totale);
		}
	    }
	}else if(i_demarre==1){
	    int num_col=part_comp[0].quark[0].num_couleur;
	    distance_carre_totale=1.e8;
	    for(int m=0;m<2;m++){
		pos_had0[m].assigne(point_nul);
		for(int ij=0;ij<2;ij++){
		    if(ij==1||m==0)
			pos_had0[m].additionne_facteur(part_comp[0].quark[ij].posit_q,0.5);
		    else
			pos_had0[m].additionne_facteur(part_comp[1].quark[num_col].posit_q,0.5);
		}
		pos_had1[m].assigne(point_nul);
		for(int ij=0;ij<3;ij++){
		    if(ij!=num_col||m==0)
			pos_had1[m].additionne_facteur(part_comp[1].quark[ij].posit_q,1./3.);
		    else
			pos_had1[m].additionne_facteur(part_comp[0].quark[0].posit_q,1./3.);
		}
		coco=0.;cici=0.;
		for(int ij=0;ij<2;ij++){
		    if(ij==1||m==0)
			tutu.assigne_soustrait(part_comp[0].quark[ij].posit_q,pos_had0[m]);
		    else
			tutu.assigne_soustrait(part_comp[1].quark[num_col].posit_q,pos_had0[m]);
		}
		coco+=tutu.longueur_carre();
		for(int ij=0;ij<3;ij++){
		    if(ij!=num_col||m==0)
			tutu.assigne_soustrait(part_comp[1].quark[ij].posit_q,pos_had1[m]);
		    else
			tutu.assigne_soustrait(part_comp[0].quark[0].posit_q,pos_had1[m]);
		}
		cici+=tutu.longueur_carre();
		if(coco+cici<distance_carre_totale){
		    distance_carre_totale=coco+cici;
		    assemblage_choisi=m;
		    
		    //System.out.println("m "+m+" distance_carre_totale "+distance_carre_totale);
		}
		
	    }
	}
	if(assemblage_choisi!=0){
	    //	System.out.print(" vatf.x "+(float)vatf.x+" vatf.y "+(float)vatf.y+" watf.x "+(float)watf.x+" watf.y "+(float)watf.y);
	    part_comp[0].pos_part.assigne(pos_had0[assemblage_choisi]);
	    part_comp[1].pos_part.assigne(pos_had1[assemblage_choisi]);
	    tata.assigne_additionne(part_comp[0].pos_part,part_comp[1].pos_part);
	    tete.assigne_soustrait(part_comp[0].pos_part,part_comp[1].pos_part);
	    //System.out.print(" tata.x "+(float)tata.x+" tata.y "+(float)tata.y+" tete.x "+(float)tete.x+" tete.y "+(float)tete.y);
	    if(i_demarre==0)
		for(int ij=1;ij<3;ij++){
		    if(num_had_init0[ij][assemblage_choisi]!=0){
			part_comp[0].quark_fake=part_comp[num_had_init0[ij][assemblage_choisi]].quark[ij];
			part_comp[num_had_init0[ij][assemblage_choisi]].quark[ij]=part_comp[num_had_init1[ij][assemblage_choisi]].quark[ij];
			part_comp[num_had_init1[ij][assemblage_choisi]].quark[ij]= part_comp[0].quark_fake;
		    }
		}
	    else{
		int numcol=part_comp[0].quark[0].num_couleur;
		part_comp[0].quark_fake=part_comp[0].quark[0];
		part_comp[0].quark[0]=part_comp[1].quark[numcol];	
		part_comp[1].quark[numcol]=part_comp[0].quark_fake;
	    }
	    System.out.println("aaaaaaaaaaaaaaaaaaaasssemblage_choisi "+assemblage_choisi);
	    /*
	      int m=assemblage_choisi;
	      System.out.println(" pos_had0[m].x "+(float)pos_had0[m].x+" pos_had0[m].y "+(float)pos_had0[m].y+" pos_had1[m].x "+(float)pos_had1[m].x+" pos_had1[m].y "+(float)pos_had1[m].y);
	    */
	    tata.soustrait(vatf);
	    //tata.print(" tata ");
	    tete.soustrait(watf);
	    //tete.print(" tete ");
	    tutu.assigne_additionne(tata,tete);
	    tutu.multiplie_cst(0.5);
	    part_comp[0].pos_part.soustrait(tutu);
	    tutu.assigne_soustrait(tata,tete);
	    tutu.multiplie_cst(0.5);
	    part_comp[1].pos_part.soustrait(tutu);
	    /*
	      vatf.assigne_additionne(part_comp[0].pos_part,part_comp[1].pos_part);
	      watf.assigne_soustrait(part_comp[0].pos_part,part_comp[1].pos_part);
	      
	      System.out.print(" vatf.x "+(float)vatf.x+" vatf.y "+(float)vatf.y+" watf.x "+(float)watf.x+" watf.y "+(float)watf.y);
	    */
	    //part_comp[0].pos_part.print("part_comp[0].pos_part ");
	    //	part_comp[1].pos_part.print("part_comp[1].pos_part ");
	    //part_comp[100]=null;
	}
    }
    void compense_par_quarks_et_hadrons(int n_q,int nf,double c,int ind,int[] liste, int nb_eli){
	double a=n_q/m_quarks;
	if(n_mesons>0)
	    a+=(double)(n_q*n_q)/(double)(nf*nf)*n_mesons/m_pi_meson;
	if(n_baryons>0)
	    a+=(double)(n_q*n_q)/(double)(nf*nf)*n_baryons/m_baryon;
	double b=0.;
	for(int m=0;m<nf;m++)
		b-=((double)n_q/nf*part_comp_finale[m].vit_part.somme_comp());
	//System.out.println("vatf.somme_comp() "+vatf.somme_comp()+" b "+(float)b);
	for(int m=0;m<n_particules_initiales;m++){
	    if(selectionnee(m,nb_eli))
		b+=part_comp[m].vit_part.somme_comp();
	}
	solu_impossible[ind]=solution_minimale_sec_deg(a,b,c);
	if(!solu_impossible[ind]){
	    for(int m=0;m<n_particules_initiales;m++){
		if(selectionnee(m,nb_eli))
		    part_comp[m].vit_part.additionne_diviseur(dp_p_sec_deg,m_quarks);
	    }
	    for(int m=0;m<nf;m++)
	if(part_comp_finale[m].masse>m_pi_meson+.001)
		    part_comp_finale[m].vit_part.additionne_facteur(dp_p_sec_deg,-(double)n_q/(nf*m_baryon));
		else
		    part_comp_finale[m].vit_part.additionne_facteur(dp_p_sec_deg,-(double)n_q/(nf*m_pi_meson));
	}
    }
    boolean selectionnee(int m,int nb_elem){
	for(int jj=0;jj<nb_elem;jj++){
	    if(m==liste_des_elimines[jj])
		return false;
	    }
	    return true;
    }
    void compense_quadrimoment_par_quarks_seuls(double c,int ind,int nb_eli){
	int nn=(n_particules_initiales-nb_eli)/2;
	double a=(n_particules_initiales-nb_eli)/m_quarks;
	double b=0.,moyenne=0.;
	//System.out.println("vatf.somme_comp() "+vatf.somme_comp()+" b "+(float)b);
	for(int kk=0;kk<n_particules_initiales;kk++)
	    part_comp[kk].terme_b_sec_deg=part_comp[kk].vit_part.somme_comp();
	for(int kk=0;kk<n_particules_initiales;kk++){
	    if(selectionnee(kk,nb_eli)){
	    part_comp[kk].classement=0;
	    for(int kk1=0;kk1<n_particules_initiales;kk1++)
		if(selectionnee(kk1,nb_eli)){
		       if(kk1!=kk&&part_comp[kk1].terme_b_sec_deg>part_comp[kk].terme_b_sec_deg)
			   part_comp[kk].classement++;
		}
	    }
	}
	for(int kk=0;kk<n_particules_initiales;kk++)
	    if(selectionnee(kk,nb_eli)){
		   if(part_comp[kk].classement>=nn)
		       b+=part_comp[kk].terme_b_sec_deg;
		   else
		       b-=part_comp[kk].terme_b_sec_deg;
	    }
	solu_impossible[ind]=solution_minimale_sec_deg(a,b,c);
	if(!solu_impossible[ind])
	   for(int kk=0;kk<n_particules_initiales;kk++)
	       if(selectionnee(kk,nb_eli))
		   if(part_comp[kk].classement>=nn)
		       part_comp[kk].vit_part.additionne_diviseur(dp_p_sec_deg,m_quarks);
		   else
		       part_comp[kk].vit_part.soustrait_diviseur(dp_p_sec_deg,m_quarks);
	//part_comp[3000]=null;
    }
    void compense_quadrimoment_par_hadrons_seuls(int nf,double c,int ind){
	int nn=nf/2;
	double a=0.;double rap_sup=(float)(nf-nn)/(float)nn;
	for(int kk=0;kk<nf;kk++)
	    if(kk>=nn)
		a+=1./part_comp_finale[kk].masse;
	    else
		a+=rap_sup*rap_sup/part_comp_finale[kk].masse;
	double b=0.;
	//System.out.println("vatf.somme_comp() "+vatf.somme_comp()+" b "+(float)b);
	for(int kk=0;kk<nf;kk++)
	    if(kk>=nn)
		b+=part_comp_finale[kk].vit_part.somme_comp();
	    else
		b-=rap_sup*part_comp_finale[kk].vit_part.somme_comp();
	solu_impossible[ind]=solution_minimale_sec_deg(a,b,c);
	//dp_p_sec_deg.print(" dp_p_sec_deg ");
	if(!solu_impossible[ind])
	    for(int kk=0;kk<nf;kk++)
		if(kk>=nn)
		    part_comp_finale[kk].vit_part.additionne_diviseur(dp_p_sec_deg,part_comp_finale[kk].masse);
		else
		    part_comp_finale[kk].vit_part.soustrait_facteur(dp_p_sec_deg,rap_sup/part_comp_finale[kk].masse);
    }
    boolean solution_minimale_sec_deg(double a,double b,double c){
	delta_sec_deg=b*b-4*a*c;
	if(delta_sec_deg<0){
	    dp_p_sec_deg.assigne(1/0.,1/0.);
	    System.out.println("a "+(float)a+" b "+(float)b+" c "+(float)c+" delta_sec_deg "+(float)delta_sec_deg);
	    //System.out.println(" nouveau c"+c+" dp_sec_deg "+dp_sec_deg);
	    //if(solu_impossible[4])
	    //	part_comp_finale[250]=null;
	    return true;
	}else{
	    if(b>0)
		dp_sec_deg=(-b+Math.sqrt(delta_sec_deg))/(2*a);
	    else
		dp_sec_deg=(-b-Math.sqrt(delta_sec_deg))/(2*a);
	    dp_p_sec_deg.assigne(dp_sec_deg,dp_sec_deg);
	    //System.out.println("a "+(float)a+" b "+(float)b+" c "+(float)c+" delta_sec_deg "+(float)delta_sec_deg+" dp_sec_deg "+(float)dp_sec_deg);
	    return false;
	}
    }
    void quadrimoment_tot_quarks_hadrons(String st, boolean avec_calcul_e_pot,boolean imprime_tout,boolean imprime_resultat){
	//if(n_particules_finales>0)
	//    part_comp_finale[0].quadrim.print("00 " +part_comp_finale[0].quadrim );
	if(imprime_tout)
	    System.out.println(st);
	quad_quarks_seuls.zero();
	if(avec_calcul_e_pot)
	    calcul_e_pot();
	for(int i=0;i<n_particules_initiales;i++)
	    if(!part_comp[i].quark[0].hadronise){
		part_comp[i].quadrimoment_part();
		if(imprime_tout)
		    part_comp[i].quadrim.print("i "+i+" part_comp[i].quadrim ");
		//System.out.println("part_comp[i].quark[0].num_couleur "+part_comp[i].quark[0].num_couleur);
		//if(n_particules_finales>0)
		//	part_comp_finale[0].quadrim.print("$i "+i+ part_comp_finale[0].quadrim );	    
		quad_quarks_seuls.additionne(part_comp[i].quadrim);
	    }  
	quad_quarks_hadrons.assigne(quad_quarks_seuls);
	//if(n_particules_finales>0)
	//part_comp_finale[0].quadrim.print("2 part_comp_finale[0].quadrim ");
	for(int j=0;j<n_particules_finales;j++){
	    part_comp_finale[j].quadrimoment_part();
	    //part_comp_finale[j].vit_part.print("part_comp_finale[j].masse "+(float)part_comp_finale[j].masse+" part_comp_finale[j].vit_part.print ");
	    quad_quarks_hadrons.additionne(part_comp_finale[j].quadrim);
	    if(imprime_tout)
		part_comp_finale[j].quadrim.print("j "+j+" part_comp_finale[j].quadrim ");	
	    /* 
	       if(Math.abs(part_comp_finale[j].pos_part_int.x)<100&&Math.abs(part_comp_finale[j].pos_part_int.y)<100){
	       part_comp_finale[j].pos_part_int.print("  part_comp[j].pos_part_int.print ");
	       part_comp_finale[j].pos_part.print("part_comp_finale[j].pos_part");
	       part_comp[1251]=null;
	       }
	    */
	}
	if(imprime_resultat||imprime_tout){
	    quad_quarks_hadrons.print( "quad_quarks_hadrons "+st);
	    quad_quarks_seuls.print("quad_quarks_seuls "+st);
	}
    }
    void calcul_e_pot(){
	for(int i=0;i<n_particules_initiales;i++)
	    part_comp[i].e_pot=0.;
	//if(n_particules_finales>0)
	//part_comp_finale[0].quadrim.print("11 "+ part_comp_finale[0].quadrim );
	for(int i=0;i<n_particules_initiales;i++){
	    if(!part_comp[i].quark[0].hadronise)
		for(int j=i+1;j<n_particules_initiales;j++)
		    if(!part_comp[j].quark[0].hadronise){
			boolean meme_couleur=part_comp[i].quark[0].num_couleur==part_comp[j].quark[0].num_couleur;
			double kkk=raideur;
			//if(!meme_couleur)
			//kkk=raideur/2.;
			vatf.assigne_soustrait(part_comp[i].pos_part,part_comp[j].pos_part);
			if(vatf.longueur_carre()<dist_lim_pot*dist_lim_pot||meme_couleur){
			    //if(!meme_couleur)System.out.println(" kkk "+kkk);
			    cucu=0.25*kkk*vatf.longueur_carre();
			    part_comp[i].e_pot+=cucu;// le 0.25 pour pouvoir additionner les energies potentielles et retrouver le 0.5
			    part_comp[j].e_pot+=cucu;
			}
		    }
	}
    }
    void ecrire_evt_prec(int nature,point_int ppp){
	if(i_demarre!=2||limite_d_ecriture_depassee||mode_continu)
	    return;
	evenement_precedent[iecr_evt_prec++]=nature;
	if(nature==-10000)
	    evenement_precedent[iecr_evt_prec-2]=-500;
	//if(iecr_evt_prec<150)
	ppp.print(" iecr_evt_prec "+iecr_evt_prec+" nature "+nature+" parite_pedago "+parite_pedago+" ppp ");
	if(nature==-10000||nature==-100||nature==-500){
	    //System.out.println(" iecr_evt_prec "+iecr_evt_prec+" nature "+nature);
	    return;
	}
	if(iecr_evt_prec>39950){
	    evenement_precedent[iecr_evt_prec++]=-500;    
	    evenement_precedent[iecr_evt_prec++]=-10000;
	    limite_d_ecriture_depassee=true;
	}
	boolean ok=false;
	for(int i=0;i<8;i++){
	    if(nature==nb_essai[i]){
		ok=true;
		break;
	    }
	}
	if(!ok){
	    System.out.println(" nature"+nature);
	    part_comp[1212]=null;
	}
	evenement_precedent[iecr_evt_prec++]=ppp.x;
	evenement_precedent[iecr_evt_prec++]=ppp.y;
	//if(iecr_evt_prec<150)
	// ppp.print(" iecr_evt_prec "+iecr_evt_prec+" nature "+nature+" ppp ");
	//if(en_train_de_lire||iecr_evt_prec>=200)
	    //if(en_train_de_lire)
	    //part_comp[1023]=null;
	//if(iecr_evt_prec<200)
	//System.out.println("fin ecrire iecr_evt_prec "+iecr_evt_prec);
    }
    boolean lire_et_peindre_evt_prec(){
    //0 gluon lié, 1,2,3 quarks des 3 couleurs, -1,-2,-3 leurs antiquarks,+100 pour un changement de couleur, gluons=quarks*10, 4 meson,5 baryon,104 et 105 pour nouveaux on verra plus tard pour les modèles. 
 
	nature_particule=evenement_precedent[ilec_evt_prec++];
	System.out.println("ilec_evt_prec "+ilec_evt_prec+" nature_particule "+nature_particule);
	//if(ilec_evt_prec>=150)
	//  part_comp[1025]=null;
 
	if(nature_particule==-10000){
	    grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
	    grph.drawString("Utilisez le menu Actions. ",100,100);
	    grph.setFont(subject.times_gras_14);
	    fin_event_naturelle=true;
	    return true;
	}
	if(nature_particule==-500){
	    if(evenement_precedent[ilec_evt_prec]!=-10000){
		if(indice_pedago<nb_images_pedago-1){
		    //if(!parite_pedago)
		    indice_pedago++;
		    //parite_pedago=!parite_pedago;
		}
		du_nouveau=true;
		System.out.println("^^^^^^^^^^^^ indice_pedago "+indice_pedago);
		for(int i=0;i<=indice_pedago;i++)
		    resume_int.dessine_resume_intermediaire(i);
	    }else{
		fin_event_naturelle=true;
	    }
	    return true;
	}
	if(nature_particule==-100)
	    return true;
	if(nature_particule==4){
	    point_int_relecture.x=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture.y=evenement_precedent[ilec_evt_prec++];
	    if(point_int_relecture.x+diametre_meson/2<(right-left)/2&&point_int_relecture.y+diametre_meson/2<(right-left)/2)
		image_meson.dessine(grph,point_int_relecture,1.,false,false);
	    return false;
	}else if(nature_particule==5){
	    point_int_relecture.x=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture.y=evenement_precedent[ilec_evt_prec++];
	    if(point_int_relecture.x+diametre_baryon/2<(right-left)/2&&point_int_relecture.y+diametre_baryon/2<(right-left)/2)
		image_baryon.dessine(grph,point_int_relecture,1.,false,false);
	    return false;
	}else if(Math.abs(nature_particule)>=10&&nature_particule<60){
	    nb_gluons_event++;
	    System.out.println(" nature_particule "+nature_particule+" nb_gluons_event "+nb_gluons_event);
	    point_int_relecture.x=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture.y=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture.print("point_int_relecture ");
	    nature_particule2=evenement_precedent[ilec_evt_prec++];
	    System.out.println("ilec_evt_prec "+ilec_evt_prec+" nature_particule2 "+nature_particule2);
	    point_int_relecture2.x=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture2.y=evenement_precedent[ilec_evt_prec++];
	    point_int_relecture2.print("point_int_relecture2 ");
	    
	    int n_p=draw_quark_issu_de_gluon(nature_particule,point_int_relecture); 
	    int n_p2=draw_quark_issu_de_gluon(nature_particule2,point_int_relecture2); 
	    
	    draw_line_anti(point_int_relecture,point_int_relecture2,coul[Math.abs(n_p)].col_pale,coul[Math.abs(n_p2)].col_pale,grph,0,diametre_quarks,7);
	    if(nb_gluons_event==1){
		toto_int.assigne_int(point_int_relecture);
		toto_int.additionne(deplace_pour_plan);
		vatf_int.assigne_int(point_int_relecture2);
		vatf_int.additionne(deplace_pour_plan);
		draw_line_anti(toto_int,vatf_int,coul[Math.abs(n_p)].col_pale,coul[Math.abs(n_p2)].col_pale,grph,0,diametre_quarks/4,3);
	    }
	}
	return false;
    }
    int draw_quark_issu_de_gluon(int n_pa,point_int p_i){
	int n_p=n_pa/10;
	if(n_p>0&&n_p<=3)
	    n_p--;
	if(n_p<0&&n_p>=-3)
	    n_p++;
	antiqqq=n_p<0;
	//dessine_quark(point_int_relecture,neuve,Math.abs(n_p),antiqqq,false,ccccc,toto_int);
	image_quark[Math.abs(n_p)].dessine(grph,p_i,1.,false,antiqqq);
	return n_p;
    }
    
    public void debuter_nouvel_event(){
	debuter_new_event=false;
	parite_pedago=false;
	attendre_signal_new_event=false;
	fin_event_naturelle=false;
	if(evenement_termine_exterieurement){
	    System.out.println(" debuter_nouvel_event evenement_termine_exterieurement "+evenement_termine_exterieurement);
	    //part_comp[766]=null;
	}
	evenement_termine_exterieurement=false;
	limite_d_ecriture_depassee=false;
	nb_de_pas_a_la_fin=0;
	if(i_demarre==2)
	    for (int i=0;i<nb_images_pedago;i++)
		resume_int.nb_comment[i]=0;
	System.out.println("debuter_nouvel peignant"+peignant+"creant "+creant);
	if(peignant||creant)
	    return;
	numero_evt++;
	voir_ce_qui_a_change=false;
	n_essais_paires_2_quarks_restant=0;n_baryons=0;n_mesons=0;
	n_refus_baryon=0;n_refus_paires=0;
	n_passages=0;i_print=0;recommencement=true;nb_de_coups_pour_rien=0;
	for(int j=0;j<n_particules_initiales;j++){
	    for (int i=0;i<part_comp[j].n_quark;i++)
		part_comp[j].quark[i]=null;
	    part_comp[j]=null;
	}
	for(int j=0;j<n_particules_finales;j++)
	    part_comp_finale[j]=null;
	for(int i=0;i<10;i++)
	    solu_impossible[i]=false;
	nb_evts_purs_hadrons=0;
	n_particules_initiales=0;
	n_particules_finales=0;
	creation_completee=false;
	cree_particules(str);
	creation_paire_deja_faite=false;
	//System.out.println(str+" n_particules_initiales "+n_particules_initiales+" diffusion "+diffusion);
	part_comp[0].pos_part.print(" part_comp[0].pos_part ");
	//if(quantique)
	//  part_comp[-1]=null;
	if(diffusion){
	    cree_particules(str1);
	    debut_event=true;iecr_evt_prec=0;
	    nb_gluons_event=0;
	    du_nouveau=false;
	    System.out.println(str1+" n_particules_initiales "+n_particules_initiales);
	    melange=false;
	    melange_p=false;
	    melange_initial=false;
	    if(!relire_l_evenement&&i_demarre==2){
		indice_pedago=0;n_quarks_pedago=0;
		for(int i=0;i<nb_images_pedago;i++)
		    subject.eraserect(resume_int.gTampon[i],0,0,resume_int.w_h[i].y,resume_int.w_h[i].x);
	    }
	    //if(numero_evt==2)
	    //part_comp[6789]=null;
	    if(i_demarre==2){
		int nb_c=(int)(Math.random()*3);
		if(nb_c>2)
		    nb_c=2;
		if(nb_c<0)
		    nb_c=0;
		part_comp[0].quark[0].num_couleur=nb_c;
		part_comp[1].quark[0].num_couleur=nb_c;
		part_comp[0].quark[0].partenaire=1;
		part_comp[1].quark[0].partenaire=0;
		part_comp[0].quark[0].initial=true;
		part_comp[1].quark[0].initial=true;
	    }
	}
	creation_completee=true;
	est_demarre=true;
	cliquee=true;
	if(!mode_continu){
	    grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
	    grph.drawString("Pour demarrer l'evenement cliquez dans cette fenetre. ",30,100);
	    grph.setFont(subject.times_gras_14);
	    System.out.println("fin debuter_nouvel_evenement");
	}
    }	    
    public boolean  forces_part_comp_diff(){
	toto_melange=false;
	if((i_demarre==0)||(i_demarre==1)){
	    for(int i=0;i<part_comp[0].n_quark;i++){
		for(int j=0;j<part_comp[1].n_quark;j++){
		    vatf.assigne_soustrait(part_comp[0].quark[i].posit_q,part_comp[1].quark[j].posit_q);
		    if(vatf.longueur_carre()<dist_lim_pot*dist_lim_pot){
			toto_melange=true;
				//System.out.println("d "+d+" i "+i+" j "+j);
			toto.assigne_facteur(vatf,raideur);
			part_comp[0].quark[i].force.soustrait(toto);
			part_comp[1].quark[j].force.additionne(toto);
			part_comp[0].force_part.soustrait(toto);   
			part_comp[1].force_part.additionne(toto);
			//part_comp[0].force_part.print("part_comp[0].force_part");
		    }
				//if(i_print>-1)System.out.println("i "+i+" dquark[i].forcex "+dquark[i].forcex+" dquark[i].forcey "+dquark[i].forcey  );		   
		}
	    }
	}else if(i_demarre==2){
	    for(int i=0;i<n_particules_initiales;i++)
		part_comp[i].force_part.zero();
	    for(int i=0;i<n_particules_initiales;i++){
		for(int j=i+1;j<n_particules_initiales;j++){
		    boolean meme_couleur=part_comp[i].quark[0].num_couleur==part_comp[j].quark[0].num_couleur;
		    double kkk=raideur;
		    vatf.assigne_soustrait(part_comp[i].pos_part,part_comp[j].pos_part);
		    if(vatf.longueur_carre()<dist_lim_pot*dist_lim_pot||meme_couleur){
			//if(!meme_couleur)System.out.println(" kkk "+kkk);
			toto_melange=true;
			part_comp[i].force_part.additionne_facteur(vatf,-kkk);
			part_comp[j].force_part.additionne_facteur(vatf,kkk);
		    }
		}
	    }
	}
	return toto_melange;
    }
    public void actionPerformed(ActionEvent e){
	//while(occupied){
	    //System.out.println("celle là occupied "+occupied);
	//}
	if(subject.n_fenetres==2)
	    while(subject.fenetre[1-num_fen].occupied){
		System.out.println("autre occupied "+occupied);
	    }
	System.out.println( "occupied_interrupt "+occupied_interrupt);
	occupied_interrupt=true;
	System.out.println("occupied_interrupt "+occupied_interrupt);
	command=e.getActionCommand();
	while(occupied){
	    //System.out.println("commande bloquee"+command);
	}
	System.out.println(" command "+command);	
	if (command=="Revenir a la fenetre principale"||command=="Sortir du programme"||command=="Revenir a la fenetre initiale avec infos"){
	    le_virer=true;
	    System.out.println("apres le_virer=true; dans la fenetre"+command);
	}
	if (command=="stopper ou reprendre"){
	    desactiver=!desactiver;
	    command="";occupied_interrupt=false;
	}
	relachee=true;cliquee=false;pressee=false;draguee=false;
	System.out.println("command   "+command);
	if(command!=""){
	    Date maintenant=new Date();
	    subject.temps_initial_en_secondes=subject.temps_en_secondes(maintenant);
	    evenement_termine_exterieurement=false;
	}
	retour_traite_commande=traite_commande();
	occupied_interrupt=false;
    }
    public void goback_demo(){
	while(occupied){
	    System.out.println("ggg occupied "+occupied);
	}
	if(subject.n_fenetres==2)
	    while(subject.fenetre[1-num_fen].occupied){
	    }
	subject.terminer_demo=true;
	occupied_interrupt=false;
	System.out.println("dans goback_demo occupied "+occupied);
    }
    public boolean traite_commande (){
	//while(!subject.fini_bouge_paint){
	//    System.out.println("subject.fini_bouge_paint "+subject.fini_bouge_paint);
	//}
	if(command=="Relire l'evenement pas a pas."){
	    indice_pedago=0;
	    debut_event=true;
	    du_nouveau=false;
	    relire_l_evenement=true;
	    itpp1.setEnabled(false);
	    fin_event_naturelle=false;
	    debuter_new_event=false;
	    mode_continu=false;ilec_evt_prec=0;
	    System.out.println("commencer lecture");
	    //incr_deplace_pour_plan();
	    staccato=true;
	    System.out.println("commencer lecture staccato"+staccato);
	    en_train_de_lire=true;
	    cliquee=true;	
	    command="";
	    return true;
	}
	/*
	if(command=="Voir le graphe final de l'evenement en plein ecran."){
	    resume_int.dessine_le_dernier_plein_ecran(indice_pedago);
	    itpp1.setEnabled(true);
	    command="";
	    return true;
	}
	*/
	if(command=="Terminer cet evenement"){
	    command="";
	    evenement_termine_exterieurement=true;
	    //part_comp[767]=null;		
	    //subject.fenetre[1000]=null;
	}	   
	if((command=="Declencher un evenement")||(command=="Declencher un evenement pas a pas, par clics ")||command=="Voir les evenements en continu"){
	    fin_event_naturelle=false;
	    relire_l_evenement=false;
	    en_train_de_lire=false;
	    if(command=="Declencher un evenement pas a pas, par clics "){
		staccato=true;
		subject.eraserect(grph,0,0,bot-top,right-left);
		mode_continu=false;
	    }else{
		staccato=false;	
	    }
	    if(command=="Declencher un evenement"){
		mode_continu=false;
		subject.eraserect(grph,0,0,bot-top,right-left);
	    }
	    if (command=="Voir les evenements en continu"){
		mode_continu=true;
		subject.eraserect(grph,0,0,bot-top,right-left);
	    }
	    if(peignant)
		while(peignant){
		    System.out.println("peignant ");
		}
	    command="";
	    evenement_termine_exterieurement=true;
	    debuter_new_event=true;
	    declencher_nouvel_event=true;

	}
	if(command=="Revenir aux conditions initiales"){
	    for(int j=0;j<n_particules_initiales;j++){
		part_comp[j].pos_part.assigne(part_comp[j].pos_part_initial);
		part_comp[j].pos_part_garde.assigne(part_comp[j].pos_part_initial);
		part_comp[j].vit_part.assigne(part_comp[j].vit_part_initiale);
		part_comp[j].vit_part_garde.assigne(part_comp[j].vit_part_initiale);
		part_comp[j].puits_posit.zero();
		part_comp[j].vitesse_puits.zero();
		for (int i=0;i<part_comp[j].n_quark;i++){
		    part_comp[j].quark[i].posit_q.assigne(part_comp[j].quark[i].posit_initial);
		    part_comp[j].quark[i].posit_garde.assigne(part_comp[j].quark[i].posit_initial);
		    part_comp[j].quark[i].vit_q.assigne(part_comp[j].quark[i].vit_initiale);
		    part_comp[j].quark[i].vit_garde.assigne(part_comp[j].quark[i].vit_initiale);		}
	    }
	    if(!cdm)
		part_comp[1].vit_part.zero();
	    est_demarre=true;
	    command="";
	}
	if( command=="Arreter pour bouger")
	    dt_fenetre=0;
	if (command=="Reprendre")
	    dt_fenetre=dt_fenetre0;
	if (command=="stopper ou reprendre"){
	    command="";
	    stopper=!stopper;
	    if(stopper){
		comment="Les vitesses des elements sont annulees,les elements repartent avec une vitesse nulle.";
		System.out.println(comment); 
		for(int j=0;j<n_particules_initiales;j++){
		    part_comp[j].pos_part_garde.assigne(part_comp[j].pos_part);
		    part_comp[j].vit_part_garde.assigne(part_comp[j].vit_part);
		    part_comp[j].vit_part.zero();
		    for(int i=0;i<part_comp[j].n_quark;i++){
			part_comp[j].quark[i].vit_garde.assigne(part_comp[j].quark[i].vit_q);part_comp[j].quark[i].vit_q.zero();
			part_comp[j].quark[i].posit_garde.assigne(part_comp[j].quark[i].posit_q);
		    }
		}
	    }else{
		for(int j=0;j<n_particules_initiales;j++){
		    part_comp[j].pos_part.assigne(part_comp[j].pos_part_garde);
		    part_comp[j].vit_part.assigne(part_comp[j].vit_part_garde);
		    for(int i=0;i<part_comp[j].n_quark;i++){
			    part_comp[j].quark[i].vit_q.assigne(part_comp[j].quark[i].vit_garde);
			    part_comp[j].quark[i].posit_q.assigne(part_comp[j].quark[i].posit_garde);
			}
		}	
	    }
	}
	if (command=="desactiver ou reactiver la fenetre")
	    desactiver=!desactiver;
	if (command=="augmenter la vitesse initiale de 10 pour cent"){
	    vitsxa*=1.1;vitsxb*=1.1;
	    vitsya*=1.1;vitsyb*=1.1;
	    command="";
	}
	if (command=="diminuer la vitesse initiale de 10 pour cent"){
	    vitsxa/=1.1;vitsxb/=1.1;
	    vitsya/=1.1;vitsyb/=1.1;
	    command="";
	}
	if (command=="augmenter la distance transverse initiale de 10 pour cent")
	    y_depart_b=y_depart_a+(int)Math.round((y_depart_b-y_depart_a)*1.1);
	if (command=="diminuer la distance transverse initiale de 10 pour cent")
	    y_depart_b=y_depart_a+(int)Math.round((y_depart_b-y_depart_a)/1.1);

	if (command=="faire une statistique de l'angle de diffusion."){
	    mb=null;
	    mb=new MenuBar();
	    setMenuBar(mb);
	    //	    pack();
	    //setSize(size_x,size_y);setLocation(loc_x,loc_y);
	    statistique=new graphique("Graphe de l'angle de diffusion",0);
	    faire_stat=true;
	    debuter_new_event=true;
	    command="";subject.temps_minimum=2400;
	    mode_continu=true;
	}

	if (command=="faire une statistique du nombre de hadrons."){
	    mb=null;
	    mb=new MenuBar();
	    setMenuBar(mb);
	    //	    pack();
	    //setSize(size_x,size_y);setLocation(loc_x,loc_y);
	    statistique=new graphique("Graphe du nombre de hadrons",0);
	    faire_stat=true;
	    debuter_new_event=true;
	    nb_de_pas_a_la_fin=1;
	    command="";subject.temps_minimum=2400;
	    mode_continu=true;
	}
	/*
	if (command=="pour les gluons, ne voir que les reels."){
	    ne_voir_que_les_gluons_reels=!ne_voir_que_les_gluons_reels;   
	}
	*/
	if(command=="Nouvelles conditions initiales"){
		command="";
		debuter_new_event=true;
	}
	if (command=="Oscillateur harmonique"){
	    osc_harm=true;osc_harm_puits=false;osc_harm_limite=false;quantique=false;
	    command="";
	    debuter_new_event=true;
	}
	if (command=="Information sur ces modeles"){
	    subject.info_modeles=true;
	    subject.ecriture_aide(i_demarre);
	    subject.info_modeles=false;
	    n_info_modeles++;
	    if(n_info_modeles/2*2==n_info_modeles)
		command="";
	}
	if (command=="Oscillateur harmonique plus puits"){
	    osc_harm=false;osc_harm_puits=true;osc_harm_limite=false;quantique=false;
	    command="";
	    System.out.println(" cpmmande recue: Oscillateur harmonique plus puits");
	    debuter_new_event=true;
	}
	if (command=="Sans echange de quarks entre les hadrons"){
	    echange_quarks=false;
	    command="";
	    itr4=null;
	    MenuItem itr4=new MenuItem("Avec echange de quarks entre les hadrons");
	    System.out.println(" cpmmande recue: Oscillateur harmonique plus puits");
	    debuter_new_event=true;
	}
	if (command=="Avec echange de quarks entre les hadrons"){
	    echange_quarks=true;
	    command="";
	    itr4=null;
	    MenuItem itr4=new MenuItem("Sans echange de quarks entre les hadrons");
	    System.out.println(" cpmmande recue: Oscillateur harmonique plus puits");
	    debuter_new_event=true;
	}

	if (command=="Oscillateur harmonique limite a une distance maxi"){
	    osc_harm=false;osc_harm_puits=false;osc_harm_limite=true;quantique=false;
	    command="";
	    debuter_new_event=true;
	}
	if (command=="Puits, Mecanique quantique"){
	    osc_harm=false;osc_harm_puits=false;osc_harm_limite=false;quantique=true;
	    command="";
	    debuter_new_event=true;
	}
	return command=="";
    }
    public void traite_click(){
	int xi=ppmouseh;int yi=ppmousev;
	System.out.println("tutu xi "+xi+" yi "+yi+" pressee "+pressee+" relachee "+relachee+" cliquee "+cliquee);
	voir_ce_qui_a_change=false;
	if(cliquee){
	    pressee=false;relachee=false;
	    if(i_demarre==2){
		//while(fin_event_naturelle){
		//}
		//pressee=false; relachee=false;cliquee=false;
		for(int i=0;i<=indice_pedago;i++){

		    int d_x=resume_int.deplace[i].x+resume_int.point_orange[i].x;
		    if(xi>=d_x-image_8_orange.d/2&&xi<=d_x+image_8_orange.d/2){
			int d_y=0;
			if(!cdm)
			    d_y=resume_int.deplace[i].y+resume_int.point_orange[i].y-50;//%%
			else
			    d_y=resume_int.deplace[i].y+resume_int.point_orange[i].y;//%%
			if(yi>=d_y-image_8_orange.d/2&&yi<=d_y+image_8_orange.d/2){
			    voir_ce_qui_a_change=true;
			    i_pt_orange=i;
			    resume_int.redessine_resume_intermediaire(i);
			    cqach.ecr_comm(i);
			    cliquee=false;
			    System.out.println("aaaaaa    i "+i);
			    //while(command==""){
			    //}
			    break;
			}
		    }
		}
	    }
	    Date maintenant=new Date();
	    subject.temps_initial_en_secondes=subject.temps_en_secondes(maintenant);
	}
	
    }

    void dessineImage(Graphics g,Image im, point_int pt, int rayon_du_carre,double fct_zm ){
	//pt.print(" pt ");
	pt_dessine.assigne_int(pt);
	pt_dessine.x=(int)(pt_dessine.x*fct_zm);
	pt_dessine.x-=rayon_du_carre;pt_dessine.y-=rayon_du_carre;
	//pt_dessine.print("im "+im+" pt_dessine ");
	g.drawImage(im,pt_dessine.x,pt_dessine.y,subject.fenetre[num_fen]);
    }

    class MouseStatic extends MouseAdapter
    {
	fenetre_particules_initiales appelant;
	public MouseStatic (fenetre_particules_initiales a){
	          appelant=a;
	}
	public void mouseClicked(MouseEvent e){
	    ppmouseh=e.getX();ppmousev=e.getY();cliquee=true;pressee=false;relachee=true;
	    System.out.println("cliquee "+cliquee);
	    traite_click();
	}
	public void mousePressed(MouseEvent e){
	    ppmouseh=e.getX();ppmousev=e.getY();pressee=true;relachee=false;cliquee=false;
	    System.out.println("pressee "+pressee);
	    traite_click();
	}
	public void mouseReleased(MouseEvent e){
	    ppmouseh=e.getX();ppmousev=e.getY();pressee=false;relachee=true;
	    //cliquee=false;
	    System.out.println("relachee "+relachee);
	    traite_click();
	}
    }   
     class MouseMotion extends MouseMotionAdapter
    {
	fenetre_particules_initiales appelant;
	public MouseMotion (fenetre_particules_initiales a)
	{
	    appelant=a;
	}
	public void mouseMoved(MouseEvent e)
	{ppmouseh=e.getX();ppmousev=e.getY();draguee=false;}
	public void mouseDragged(MouseEvent e)
	{ppmouseh=e.getX();ppmousev=e.getY();draguee=true;
	System.out.println("draguee dans Mousemove "+draguee);
	traite_click();
	}
    }
    class bild{
	Image crop_bild[]=new Image[2];Image crop_pedago[]=new Image[2];;
	boolean c_est_un_quark; int d,d_pedago; 
	bild(int d1,int d_pedago1,int num_coul,boolean c_est_un_quark1){
	    d=d1;
	    d_pedago=d_pedago1;
	    c_est_un_quark=c_est_un_quark1;
	    crop_bild[0]=fabrique_image_ronde(d,num_coul,0);
	    if(d_pedago!=0)
		crop_pedago[0]=fabrique_image_ronde(d_pedago,num_coul,0);
	    if(c_est_un_quark){
		crop_bild[1]=fabrique_image_ronde(d,num_coul,1);
		if(d_pedago!=0)
		    crop_pedago[1]=fabrique_image_ronde(d_pedago,num_coul,1);
	    }
	}
	Image fabrique_image_ronde(int dd,int num_coul,int ianti ){
	    Image crp=createImage(dd,dd); 
	    Graphics gT=crp.getGraphics();
	    if(num_coul>=0)
		gT.setColor(coul[num_coul].col); 
	    else
		if(num_coul==-1)
		    gT.setColor(gris_pale);
		else if(num_coul==-2)
		    gT.setColor(Color.black);
		else if(num_coul==-3)
		    gT.setColor(Color.orange);
	    gT.fillOval(0,0,dd,dd);
	    if(c_est_un_quark){
		if(ianti==1)
		    gT.setColor(Color.black);
		else
		    gT.setColor(Color.white);
		gT.fillRect(dd/4,dd/4,dd/2,dd/2);
	    }
	    return crp;
	}
	void dessine(Graphics g,point_int pt,double fct_zm,boolean pedag,boolean antiq){
	    int ianti=0;
	    if(antiq)
		ianti=1;
	    pt_dessine.assigne_int(pt);
	    pt_dessine.x=(int)(pt_dessine.x*fct_zm);
	    if(!cdm&&pedag)//%%
		//pt_dessine.y=(int)(pt_dessine.y*fct_zm);//%%
		pt_dessine.y=(int)(pt_dessine.y*fct_zm)-50;//%%
	    if(!pedag){
		pt_dessine.x-=d/2;pt_dessine.y-=d/2;
		g.drawImage(crop_bild[ianti],pt_dessine.x,pt_dessine.y,subject.fenetre[num_fen]);
	    }else{
		pt_dessine.x-=d_pedago/2;pt_dessine.y-=d_pedago/2;
		g.drawImage(crop_pedago[ianti],pt_dessine.x,pt_dessine.y,subject.fenetre[num_fen]);
	    }
	}
    }
    class resume_intermediaire{
	point_int deplace[]=new point_int[10];
	point_int w_h[]=new point_int[10];
	point_int point_orange[]=new point_int [10];
	String commentaires[][]=new String[10][10];int nb_comment[]= new int[10];
	Image crop[]=new Image[10];
	Graphics gTampon[]=new Graphics[10];
	resume_intermediaire(boolean cm){
	    if(!cm){
		deplace[0]=new point_int(0,(bot-top)/2+150);//%%c'est ici, on a enleve 50
		deplace[1]=new point_int(20,(bot-top)/2+150);
		deplace[2]=new point_int(100,(bot-top)/2+150);
		deplace[3]=new point_int(220,(bot-top)/2+150);
		deplace[4]=new point_int(340,(bot-top)/2+150);
		deplace[5]=new point_int(460,(bot-top)/2+150);
		deplace[6]=new point_int(580,(bot-top)/2+150);
		deplace[7]=new point_int(580,(bot-top)/4+150);
		deplace[8]=new point_int(580,150);
		deplace[9]=new point_int(-1000,-1000);
		w_h[0]=new point_int(93,(bot-top)/2);
		w_h[1]=new point_int(180,(bot-top)/2);
		w_h[2]=new point_int(180,(bot-top)/2);
		w_h[3]=new point_int(180,(bot-top)/2);
		w_h[4]=new point_int(180,(bot-top)/2);
		w_h[5]=new point_int(180,(bot-top)/2);
		w_h[6]=new point_int(180,(bot-top)/2);
		w_h[7]=new point_int((right-left)/2,(bot-top)/4);
		w_h[8]=new point_int((right-left)/2,(bot-top)/4);
		w_h[9]=new point_int(10,10);
		for(int i=0;i<10;i++)
		    if(i==0)
			point_orange[i]=new point_int(15,55);
		    else if(i==1)
			point_orange[i]=new point_int(75,55);
		    else
			point_orange[i]=new point_int(110,55);
	    }else{
		deplace[0]=new point_int((right-left)/2+30,40);
		deplace[1]=new point_int((right-left)/2+30,110);
		deplace[2]=new point_int((right-left)/2+30,190);
		deplace[3]=new point_int((right-left)/2+30,270);
		deplace[4]=new point_int((right-left)/2+30,360);
		deplace[5]=new point_int((right-left)/2+30,460);
		deplace[6]=new point_int((right-left)/2+30,560);
		deplace[7]=new point_int(0,(bot-top)/2-20);
		deplace[8]=new point_int(0,(bot-top)/2+70);
		deplace[9]=new point_int(0,3*(bot-top)/4-10);
		w_h[0]=new point_int((right-left)/2-30,70);
		w_h[1]=new point_int((right-left)/2-30,80);
		w_h[2]=new point_int((right-left)/2-30,90);
		w_h[3]=new point_int((right-left)/2-30,90);
		w_h[4]=new point_int((right-left)/2-30,100);
		w_h[5]=new point_int((right-left)/2-30,100);
		w_h[6]=new point_int((right-left)/2-30,150);
		w_h[7]=new point_int((right-left)/2,140);
		w_h[8]=new point_int((right-left)/2,140);
		w_h[9]=new point_int((right-left)/2,180);
		for(int i=0;i<10;i++)
		    point_orange[i]=new point_int(50,25);
	    }
	    for(int i=0;i<10;i++){
		nb_comment[i]=0;
		crop[i]=createImage(w_h[i].x,w_h[i].y);
		//System.out.println(" i "+i+" cm "+cm+" gTampon[i] "+gTampon[i]+" crop[i] "+crop[i]);	       
		gTampon[i]=crop[i].getGraphics();
		gTampon[i].setColor(Color.white);
		subject.eraserect(gTampon[i],0,0,w_h[i].y,w_h[i].x);
		crop_copy[i]=createImage(w_h[i].x,w_h[i].y);
		gTampon_crop_copy[i]=crop_copy[i].getGraphics();
	    }

	}
	void dessine_resume_intermediaire(int num_resume){
	    if(!faire_stat){
		toto_int.assigne_int(point_orange[num_resume]);
		if(!cdm)
		    toto_int.y-=50;
		image_8_orange.dessine(gTampon[num_resume],toto_int,1.,false,false);
		grph.drawImage(crop[num_resume],deplace[num_resume].x,deplace[num_resume].y,w_h[num_resume].x,w_h[num_resume].y,subject.fenetre[num_fen]);
		System.out.println("nnnnnnn num_resume "+num_resume+" nb_images_pedago "+nb_images_pedago+" n_mesons "+ n_mesons+" n_baryons "+n_baryons);
		gTampon_crop_copy[num_resume].drawImage(crop[num_resume],0,0,w_h[num_resume].x,w_h[num_resume].y,subject.fenetre[num_fen]);
		//deplace[num_resume].print(" num_resume "+num_resume+"deplace[num_resume] ");
		if(staccato){
		    grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
		    grph.drawString(" Cliquez dans cette fenetre ou utilisez le menu Actions.",170, 100);
		}	    
		if(cdm&&num_resume==1){
		    System.out.println(" part_comp[0].quark[0].letzt.nb_pts "+part_comp[0].quark[0].letzt.nb_pts+" part_comp[1].quark[0].letzt.nb_pts "+part_comp[1].quark[0].letzt.nb_pts);
		    System.out.println(" part_comp[0].nouvelle "+part_comp[0].nouvelle+" part_comp[0].quark[0].chgt_coul "+part_comp[0].quark[0].chgt_coul);
		    System.out.println(" part_comp[1].nouvelle "+part_comp[1].nouvelle+" part_comp[1].quark[0].chgt_coul "+part_comp[1].quark[0].chgt_coul);
		    part_comp[0].r_precedent.print("part_comp[0].r_precedent.print ");
		    part_comp[1].r_precedent.print("part_comp[1].r_precedent.print ");
		    //part_comp[455]=null;
		}
	    }
	}
	void redessine_resume_intermediaire(int num_resume){
	    //if(num_resume<indice_pedago)
	    //	grph.drawImage(crop_copy[num_resume+1],deplace[num_resume+1].x,deplace[num_resume+1].y,w_h[num_resume+1].x,w_h[num_resume+1].y,null);
	    grph.drawImage(crop_copy[num_resume],deplace[num_resume].x,deplace[num_resume].y,w_h[num_resume].x,w_h[num_resume].y,subject.fenetre[num_fen]);
	    if(staccato){
		grph.setColor(Color.red);grph.setFont(subject.times_gras_24);
		grph.drawString(" Cliquez dans cette fenetre ou utilisez le menu Actions.",170, 100);
	    }

	}
	void dessine_le_dernier_plein_ecran(int num_resume){
	    grph.drawImage(crop[num_resume],0,200,right-left,bot-top-200,subject.fenetre[num_fen]);
	}
	void incremente(String st,int num_resume){
	    //System.out.println(" iiiiiiiiiiiiii num_resume "+num_resume);
	    commentaires[num_resume][nb_comment[num_resume]]=st;
	    if(nb_comment[num_resume]<9)
		nb_comment[num_resume]++;
	}
    }
    
    class ce_qui_a_change extends Frame{
	int top_change=150,left_change=300,bottom_change=300,right_change=520;
	Graphics grp_change;
	ce_qui_a_change(String s){
	    super(s);
	    pack();
	    if(!cdm){
		top_change+=100;
		bottom_change+=100;
	    }
	    setSize(right_change-left_change,bottom_change-top_change);
	    setLocation(left_change,top_change);
	    //setLocation(left,top);
	    grp_change=getGraphics();
	}
	void ecr_comm(int i_orange){
	    setVisible(true);
	    subject.eraserect(grp_change,0,0,bottom_change-top_change,right_change-left_change);
	    grp_change.setColor(Color.red);
	    System.out.println(" i_orange "+i_orange+" resume_int.nb_comment[i_orange] "+resume_int.nb_comment[i_orange]);
	    for (int i=0;i<resume_int.nb_comment[i_orange];i++){
		System.out.println("i "+i+" resume_int.commentaires[i] "+resume_int.commentaires[i_orange][i]);
		grp_change.drawString(resume_int.commentaires[i_orange][i],10,50+i*15);
	    }
	}
    }
    class graphique extends Frame {
	final int top=400;final int left=200;final int bottom = 700;final int right = 920;
	int comptage[][]=new int [3][181];Graphics grp;int echelle;
	int n_pts_graphe=0;double comptage_max=0;int num_graphe;String sstt[]=new String[3];
	Color cocol[]=new Color[3];
	public graphique(String s, int numg){
	    super(s);num_graphe=numg;
	    System.out.println("cree graphe "+s);	    
	    setSize(right-left,bottom-top);
	    setLocation(left+numg*30,top+num_fen*100+numg*30);
	    //setLocation(left,top);
	    setVisible(true);echelle=1;
	    if(i_demarre!=2){
		sstt[0]="Particule incidente, cdm, ";
		sstt[1]="Particule incidente, cible fixe, ";
		sstt[2]="Particule cible, cible fixe ";
	    }else{
		sstt[0]="Nb de mesons ";
		sstt[1]="Nb de baryons ";
		sstt[2]="Total ";
	    }
	    cocol[0]=Color.red;
	    cocol[1]=Color.blue;
	    cocol[2]=Color.black;
	    addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
			while(occupied){
			}
			le_virer=true;
		    };
	    });

	    grp= getGraphics(); 
	    grp.setColor(Color.blue);
	    for(int i=0;i<=180;i++)
		for(int j=0;j<=2;j++)
		    comptage[j][i]=0;
	    comptage_max=1.0;
	    System.out.println("grp "+grp);
	}
	void dessine_graphe (){
	    setVisible(true);
	    int ang=0;
	    if(i_demarre!=2)
		ang=(int)Math.round(angle_vitesse(part_comp[0].vit_part));
	    else
		ang=n_mesons*2+10;
	    System.out.println("ang "+ang);
	    comptage[0][ang]++;
	    if(comptage[0][ang]>comptage_max)
		comptage_max=comptage[0][ang];
	    if(i_demarre!=2){
		toto.assigne_additionne(part_comp[0].vit_part,v_du_cdm);
		ang=(int)Math.round(angle_vitesse(toto));
	    }else
		ang=n_baryons*4+60;
	    comptage[1][ang]++;
	    if(comptage[1][ang]>comptage_max)
		comptage_max=comptage[1][ang];
	    if(i_demarre!=2){
		toto.assigne_additionne(part_comp[1].vit_part,v_du_cdm);
		ang=(int)Math.round(angle_vitesse(toto));
	    }else
		 ang=(n_mesons+n_baryons)*2+120;
	    if(ang>180)
		ang=179;
	    comptage[2][ang]++;
	    if(comptage[2][ang]>comptage_max)
		comptage_max=comptage[2][ang];
	    if(2*(int)Math.round(comptage_max/((double)echelle))>bottom-top-50)
		echelle++;
	    //System.out.println(" comptage_max "+comptage_max+" echelle "+ echelle); 
	    subject.eraserect(grp,0,0,bottom-top,right-left);
	    subject.drawline_couleur(grp,0,bottom-top-26 , 720, bottom-top-26, Color.black);
	    grp.setColor(Color.black);
	    if(i_demarre!=2){
		grp.drawString("0",10,bottom-top-34);
		grp.drawString("45",180,bottom-top-34);
		grp.drawString("90",360,bottom-top-34);
		grp.drawString("135",540,bottom-top-34);
		grp.drawString("180 degres",700,bottom-top-34);
	    }else{
		grp.setColor(Color.blue);
		grp.drawString("Nb de mesons",40,150);
		grp.drawString("Nb de baryons",240,150);
		grp.drawString("mesons+baryons",480,150);
		grph.setColor(Color.black);
		grp.setFont(subject.times_gras_14);
		grp.drawString("0",40,bottom-top-34);
		grp.drawString("5",80,bottom-top-34);
		grp.drawString("10",120,bottom-top-34);
		grp.drawString("0",240,bottom-top-34);
		grp.drawString("2",272,bottom-top-34);
		grp.drawString("4",304,bottom-top-34);
		grp.drawString("0",480,bottom-top-34);
		grp.drawString("5",520,bottom-top-34);
		grp.drawString("10",560,bottom-top-34);
	    }
	    for(int k=0;k<=2;k++){
		grp.setColor(cocol[k]);
		double moyenne=0.,moyenne_2=0.; int comptage_tot=0;
		for(int i=0;i<=180;i++){
		    if(comptage[k][i]>0){
			int yy=bottom-top-32-2*(int)Math.round((comptage[k][i]+1)/((double)echelle));
			int i4=i*4;
			for(int j=-1;j<=1;j++)
			    grp.drawLine(i4-1,yy+j,i4+1,yy+j);
			if(comptage[k][i]>1){
			    if(i_demarre!=2){
				moyenne+=i*comptage[k][i];
				comptage_tot+=comptage[k][i];
				moyenne_2+=i*i*comptage[k][i];
			    }else{
				if(k==0){
				    if(i/2*2==i&&i<60){
					moyenne+=(i/2-5)*comptage[k][i];
					comptage_tot+=comptage[k][i];
					moyenne_2+=(i/2-5)*(i/2-5)*comptage[k][i];
				    }
				}else if(k==1){
				    if(i/4*4==i&&i>=60&&i<120){
					moyenne+=(i/4-15)*comptage[k][i];
					comptage_tot+=comptage[k][i];
					moyenne_2+=(i/4-15)*(i/4-15)*comptage[k][i];
				    }
				}else if(k==2){
				    if(i/2*2==i&&i>=120){
					moyenne+=(i/2-60)*comptage[k][i];
					comptage_tot+=comptage[k][i];
					moyenne_2+=(i/2-60)*(i/2-60)*comptage[k][i];
				    }
				}
			    }
			}   
		    }
		}
		if(comptage_tot>5){
		    moyenne/=comptage_tot;moyenne_2/=comptage_tot;
		    moyenne_2-=moyenne* moyenne;moyenne_2=Math.sqrt(moyenne_2);
		    double imoy=Math.round(moyenne*10.)/10.;double imoy_2=Math.round(moyenne_2/Math.sqrt(comptage_tot)*100.)/100.;
		    if(i_demarre!=2){
			grp.drawString(sstt[k]+" moyenne "+imoy+" degres, + ou - "+imoy_2,260,50+k*16);
		    }else{
			grp.drawString(sstt[k]+" moyenne "+imoy+" + ou - "+imoy_2,260,50+k*16);
		    }
		}else
		    grp.drawString(sstt[k],260,50+k*16);
	    }
	    grp.setColor(Color.black);
	    grp.drawLine(0,bottom-top-35,right,bottom-top-35);	    
	}
	double angle_vitesse(point vvv){
	    angle_vit=Math.asin(-vvv.y/vvv.longueur());
	    if(angle_vit<0.)angle_vit=pi+angle_vit;
	    //if(part_comp[0].vvv.y>0.)angle=-angle+2.*pi;
	    angle_vit*=180./pi;
	    if( Math.abs(angle_vit)>181.)
		vvv.print("angle_vit "+angle_vit+" vvv "); 
	    return Math.abs(angle_vit);
	}

   }
    abstract class particule{
	point pos_part,d_pos_part,pos_part_garde,vit_part,dvit_part,vit_part_garde,toto,vatf,pos_part_initial,vit_part_initiale;
	point vit_part_sauv;double terme_b_sec_deg=0.;
	double masse=0;String nom_part;
	point_int r_precedent,r_dernier;
	boolean nouvelle=true,mere_deja_dessinee=false;
	double e_pot=0.;
	point_int pos_part_int,pos_part_int_labo;
	point pos_part_dbl_labo;
	quadrimoment quadrim;
	int diametre,num_part;int classement;
	public particule(point_int r_ini,int diametre1,point speed,String s,int num_part1){
	    //r_ini.print(" r_ini "); speed.print(" speed ");
	    r_precedent=new point_int(r_ini);
	    r_dernier=new point_int(r_ini);
	    //System.out.println(" s "+s);
	    //System.out.println("num_part1  "+num_part1); 
	    diametre=diametre1;num_part=num_part1;
	    pos_part=new point(r_ini);
	    d_pos_part=new point(point_nul);
	    if(i_demarre==-1&&!quantique)
		pos_part.assigne(point_nul);
	    pos_part_garde=new point(pos_part);
	    pos_part_int=new point_int(pos_part);
	    pos_part_int_labo=new point_int(point_nul_int);
	    pos_part_dbl_labo=new point(point_nul);
	    //pos_part.print("pos_part ");
	    vit_part=new point(speed.x,speed.y);
	    if(i_demarre==-1)
		vit_part.assigne(point_nul);
	    dvit_part=new point(0,0);
	    vit_part_sauv=new point(vit_part);
	    //vit_part.print("vit_part ");
	    quadrim=new quadrimoment(0.,0.,point_nul);
	    pos_part_initial=new point(pos_part);
	    //pos_part_initial.print("pos_part_initial ");
	    vit_part_initiale=new point(vit_part);
	    //vit_part_initiale.print("vit_part_initiale ");
	    toto=new point(0,0);vatf=new point(0,0);
	}
	void quadrimoment_part(){
	    quadrim.assigne(masse,e_pot,vit_part);
	}
	abstract void dessine_part(boolean enregistrer);
	abstract void bouge();
    }
    class particule_finale extends particule{
	point_int posit_fenetre;
	bild image;int diametre_pedago;
	int num_part_ini[]=new int [3];ligne_coloree ligne_col_finale[]=new ligne_coloree[3];
	public particule_finale(point_int r_ini,int diametre1,point speed,String s,int num_part1,int num_part_ini_0,int num_part_ini_1,int num_part_ini_2){
	    super(r_ini,diametre1,speed,s,num_part1);
	    nom_part=s;
	    num_part_ini[0]=num_part_ini_0;
	    num_part_ini[1]=num_part_ini_1;
	    num_part_ini[2]=num_part_ini_2;
	    tata_int.assigne_int(r_ini);
	    if(!cdm){
		if(s=="meson")
		    tata_int.x+=2*diametre_meson_pedago;
		else if(s=="baryon")
		    tata_int.x+=2*diametre_baryon_pedago;
	    }else{
		tata_int.y-=(ordonnee_initiale-50);	
		if(s=="meson"){
		    tutu_int.assigne_soustrait(part_comp[num_part_ini[0]].r_dernier,part_comp[num_part_ini[1]].r_dernier);
		    if(!(Math.abs(tutu_int.x)>80.&&Math.abs(tutu_int.y)<10))
			if(tata_int.x>abscisse_initiale)
			    tata_int.x+=2*diametre_meson_pedago;
			else
			    tata_int.x-=2*diametre_meson_pedago;
		}
	    }
	    for (int i=0;i<3;i++){
		System.out.println(" i "+i+" num_part_ini[i] "+num_part_ini[i]); 
		if(num_part_ini[i]!=-1){
		    tete_int.assigne_int(part_comp[num_part_ini[i]].r_dernier);
		    if(cdm)
			tete_int.y-=(ordonnee_initiale-50);	
		    ligne_col_finale[i]=new ligne_coloree(part_comp[num_part_ini[i]].quark[0].num_couleur,tata_int,tete_int);
		    ligne_col_finale[i].print("  i "+i+" num_part_ini[i] "+num_part_ini[i]+" ligne_col_finale[i] ");
		}
	    }
	   if(s=="meson"){
		masse=m_pi_meson;
		diametre_pedago=diametre_meson_pedago;
		image=image_meson;
	   }else if(s=="baryon"){
	       masse=m_baryon;
	       diametre_pedago=diametre_baryon_pedago;
	       image=image_baryon;
	   }
	    e_pot=0.;
	    quadrimoment_part();
	}
	void bouge(){
	    toto.assigne(vit_part);toto.multiplie_cst(dt_fenetre);
	    pos_part.additionne(toto);
	}
	void dessine_part(boolean enregistrer){
	    if(pos_part_int.x+diametre/2<(right-left)/2&&pos_part_int.y+diametre/2<(right-left)/2)
		image.dessine(grph,pos_part_int,1.,false,false);
	    if(nouvelle&&!relire_l_evenement){
		//toto_int.assigne_diviseur_sur_y(pos_part_int,2);
		point_int pp_i=new point_int(pos_part_int);
		if(cdm){
		    pp_i.y-=(ordonnee_initiale-50);
		    if(nom_part=="meson"){
			toto_int.assigne_soustrait(ligne_col_finale[0].p2,ligne_col_finale[1].p2);
			if(!(Math.abs(toto_int.x)>80.&&Math.abs(toto_int.y)<10))
			    if(pp_i.x>abscisse_initiale)
				pp_i.x+=2*diametre_pedago;
			    else
				pp_i.x-=2*diametre_pedago;
		    }
		}else
		    pp_i.x+=2*diametre_pedago;
		pp_i.print(" pp_i ");
		for(int i=indice_pedago;i<nb_images_pedago;i++){
		    image.dessine(resume_int.gTampon[i],pp_i,fact_zoom,true,false);
		    //ligne_c0.print(" ligne_c0 ");
		    ligne_col_finale[0].dessine(resume_int.gTampon[i]);
		    ligne_col_finale[1].dessine(resume_int.gTampon[i]);
		    if(nom_part=="baryon")
			ligne_col_finale[2].dessine(resume_int.gTampon[i]);
		}
	    }
	    if(enregistrer&&i_demarre!=-1)
		if(nom_part=="meson")
		    ecrire_evt_prec(4,pos_part_int);
		else if(nom_part=="baryon")
		    ecrire_evt_prec(5,pos_part_int);
	}
    }
    class particule_initiale extends particule{
	boolean reflexion=false,a_reflechi=false;double energie_part,energie_cin=0.,energie_pot=0.;
	boolean a_ete_dessinee=false,a_deja_cree=false,initiale=false;;
	point force_part;
	double e_init_dans_mouvement=0,e_cin_init_dans_mouvement=0,e_pot_init_dans_mouvement=0;
	point posit_cm_quarks,posit_cm_quarks0,posit_cm_tot,puits_posit,vitesse_puits;
	ligne_coloree ligne_col;
	int particule_mere=-1;
	point vit_cm,dpuits_posit;
	point_int puits_posit_fenetre,posit_cm_tot_int;
	double masse_des_quarks;
	quark quark[]= new quark[3];
	int n_quark;    quark quark_fake;
	point v_cm,r_cm,ri_cm1;point dist_q;boolean corrige=false;
	point ri_cm[]=new point[3];point vi_cm[]=new point[3];
	point rr[]=new point[3];point vvv[]=new point[3];
	public particule_initiale(point_int r_ini,int diametre1,point speed,String s,int num_part1,int mere){
	    super(r_ini,diametre1,speed,s,num_part1);
	    nom_part=s;
	    //System.out.println("n_quark"+n_quark+" num_part "+num_part);
	    posit_cm_quarks=new point(0,0);posit_cm_quarks0=new point(0,0);
	    for(int i=0;i<num_part;i++){
		tutu_int.assigne_soustrait(r_dernier,part_comp[i].r_dernier);
		if(tutu_int.longueur_carre()<Math.pow(diametre_quarks/2,2))
		    r_dernier.y=part_comp[i].r_dernier.y+diametre_quarks/2;
	    }
	    force_part=new point(0,0);
	    vitesse_puits=new point(0,0);
	    vit_cm=new point(0,0);
	    particule_mere=mere;
	    ligne_col=new ligne_coloree(-1,point_nul_int,point_nul_int);
	    posit_cm_tot=new point(0,0);
	    dpuits_posit=new point(0,0);
	    posit_cm_tot_int=new point_int(0,0);
	    puits_posit=new point(0,0);
	    vit_part_garde=new point(0,0);
	    if(i_demarre==0||i_demarre==1)
		quark_fake=new quark(point_nul,point_nul,masse_quark,diametre,0,false,-1);
	    quadrimoment_part();
	    //pos_part_initial.print("pos_part_initial ");
	    puits_posit_fenetre=new point_int(0,0);
	    puits_posit_fenetre.assigne(pos_centre);
	    v_cm=new point (0,0);
	    r_cm=new point (0,0);ri_cm1=new point (0,0);	    
	    n_quark=0;
	    for (int i=0;i<3;i++){
		rr[i]=new point(0,0);
		ri_cm[i]=new point(0,0);
		vvv[i]=new point(0,0);
		vi_cm[i]=new point(0,0);
	    }
	    dist_q=new point(0,0);

	    if(s=="proton"){
		rr[0].rand((double)diametre/2.);
		rr[1].rand((double)diametre/2.);
		
		//rr[0].x=0.9*diametre/2.;
		//rr[0].y=0.;
	       //rr[1].x=0.;
	       //rr[1].y=0.9*diametre/2.;;
		
		rr[2].zero();rr[2].soustrait(rr[0]);rr[2].soustrait(rr[1]);
		
		rr[0].print("rr[0]");
		if(rr[2].longueur()>diametre/2-1){
		    for (int i=0;i<3;i++)
			rr[i].multiplie_cst((diametre/2-1)/rr[2].longueur());
		}
		vvv[0].rand(40.);
		//vvv[0].x=40.;
		//vvv[0].y=40.;
		toto.assigne(rr[0]);toto.soustrait(rr[2]);
		vatf.assigne_facteur(vvv[0],masse_quark);
		double vect0=toto.produit(vatf);
		vvv[1].rand(40.);
		//vvv[1].x=-40.;
		//vvv[1].y=-40.;
		toto.assigne(rr[1]);toto.soustrait(rr[2]);
		vatf.assigne_facteur(vvv[1],masse_quark);
		double vect1=toto.produit(vatf);
		double vect2=vect0+vect1;
		if(Math.abs(toto.x)>Math.abs(toto.y))
		    vvv[1].y-=vect2/toto.x/masse_quark;
		else
		    vvv[1].x+=vect2/toto.y/masse_quark;
		
		vvv[2].zero();vvv[2].soustrait(vvv[0]);vvv[2].soustrait(vvv[1]);
		vvv[0].print("vvv[0]");
		for (int i=0;i<3;i++){
		    System.out.println("i "+i);
		    quark[i]=new quark(rr[i],vvv[i],masse_quark,diametre_quarks,i,false,i);
		    quark[i].posit_q.print("****i "+i+" quark[i].posit_q ");
		    n_quark=i+1;
		}
		raideur=0.02;
		masse=masse_proton;
	    }
	    if(s=="meson"){
		n_quark=2;
		rr[0].rand((double)diametre/2.);
		rr[1].zero();rr[1].soustrait(rr[0]);
		vvv[0].assigne(rr[0]);vvv[0].multiplie_cst((0.5-Math.random())*60./rr[0].longueur());
		//vvv[0].rand(60.);
		vvv[1].zero();vvv[1].soustrait(vvv[0]);
		int nb_c=(int)(Math.random()*3);
		if(nb_c>2)
		    nb_c=2;
		if(nb_c<0)
		    nb_c=0;
		for (int i=0;i<2;i++){
		    if(i==0)
			quark[i]=new quark(rr[i],vvv[i],masse_quark,diametre_quarks,nb_c,false,i);
		    else
			quark[i]=new quark(rr[i],vvv[i],masse_quark,diametre_quarks,nb_c,true,i);
		    n_quark=i+1;
		}
		raideur=0.02;
		masse=masse_meson;
	    }
	    if((s=="quark")||(s=="antiquark")){
		masse=m_quarks;
		osc_harm=false;osc_harm_puits=false;osc_harm_limite=true;quantique=false;
		n_quark=1;
		rr[0].zero();vvv[0].zero();
		if(s=="quark"){
		    nb_couleur_q_qb=(int)(Math.random()*3);
		    if(nb_couleur_q_qb>2)
			nb_couleur_q_qb=2;
		    if(nb_couleur_q_qb<0)
			nb_couleur_q_qb=0;
		    quark[0]=new quark(rr[0],vvv[0],masse_quark,diametre_quarks,nb_couleur_q_qb,false,0);
		    //System.out.println("£££££n_particules_initiales "+n_particules_initiales+" nb_couleur_q_qb "+nb_couleur_q_qb);
		    
		}else{// l'antiquark vient juste après le quark et le nb_couleur_q_qb n'a pas encore changé.
		    quark[0]=new quark(rr[0],vvv[0],masse_quark,diametre_quarks,nb_couleur_q_qb,true,0);
		    //System.out.println("ùùùùùn_particules_initiales "+n_particules_initiales+" nb_couleur_q_qb "+nb_couleur_q_qb);
		    
		}
	    if(particule_mere!=-1)
		ligne_col.assigne(quark[0].num_couleur,r_precedent,part_comp[particule_mere].r_precedent);

		raideur=0.01;
		dist_lim_pot=120.;
		masse=masse_quark;   
	    }
	    energie_part=energie_totale();
	    energie_init=energie_totale();
	    posit_cm_quarks0.zero();
	    masse_des_quarks=0.;
	    for(int ij=0;ij<n_quark;ij++)
		masse_des_quarks+=quark[ij].masse;
	    for(int ij=0;ij<n_quark;ij++)
		posit_cm_quarks0.additionne_facteur(quark[ij].posit_q,quark[ij].masse/masse_des_quarks);
	    
	}
	void calcule_coordonnes_int(){
	    pos_part_int.assigne(pos_part);
	    if(num_part==0){
		pos_part_dbl_labo.x=pos_part.x+x_du_cdm-abscisse_initiale;
		//pos_part_dbl_labo.x=pos_part.x+x_du_cdm;
		pos_part_int_labo.x=(int)Math.round(pos_part_dbl_labo.x);
	    }else{
		pos_part_dbl_labo.x=(x_du_cdm*(part_comp[0].masse+part_comp[1].masse)-part_comp[0].masse*part_comp[0].pos_part_dbl_labo.x)/part_comp[1].masse;
		pos_part_int_labo.x=(int)Math.round(pos_part_dbl_labo.x);
		/*
		toto.assigne_additionne(pos_part,part_comp[0].pos_part);
		toto.multiplie_cst(0.5);
		tete.assigne_additionne(vit_part,part_comp[0].vit_part);
		tete.multiplie_cst(0.5);
		coco=(pos_part_dbl_labo.x+part_comp[0].pos_part_dbl_labo.x)/2;
		toto.print(" x_du_cdm "+(float)x_du_cdm+" coco "+(float)coco+" tete.x "+tete.x+" toto ");
		if(Math.abs(toto.x-450)>4)
		    part_comp[100]=null;
		*/
	    }
	    pos_part_int_labo.y=(int)(pos_part.y+diff_y_labo_cdm);
	    /*
	    if(num_part==1){
		toto.assigne_soustrait(pos_part,part_comp[0].pos_part);
		tata.assigne_soustrait(pos_part_dbl_labo,part_comp[0].pos_part_dbl_labo);
		System.out.println(" toto.x "+(float)toto.x+" toto.y "+(float)toto.y+" tata.x "+(float)tata.x+" tata.y "+(float)tata.y);

		toto_int.assigne_soustrait(pos_part_int,part_comp[0].pos_part_int);
		tata_int.assigne_soustrait(pos_part_int_labo,part_comp[0].pos_part_int_labo);
		System.out.println(" toto_int.x "+toto_int.x+" toto_int.y "+toto_int.y+" tata_int.x "+tata_int.x+" tata_int.y "+tata_int.y);
	    //pos_part_int.print("j "+j+" anti_q "+quark[0].anti_q+" num_couleur "+quark[0].num_couleur+" nouvelle "+nouvelle+" pos_part_int ");
	    }
	    */
	    if(n_quark==1)
		quark[0].posit_q_int.assigne_int(pos_part_int);
	    else
		for(int i=0;i<n_quark;i++){
		    quark[i].posit_q_int.assigne(quark[i].posit_q);
		    if(i_demarre==0||i_demarre==1){
			quark[i].posit_q_int_labo.x=(int)(quark[i].posit_q_int.x+pos_part_int_labo.x-pos_part.x);
			quark[i].posit_q_int_labo.y=(int)(quark[i].posit_q_int.y+diff_y_labo_cdm);
		    }
		}
	}
	void dessine_puits_ou_quantique(){
	    grph.setColor(gris_pale);
	    if(quantique)
		if(nom_part=="meson")
		    image_puits_meson.dessine(grph,pos_part_int,1.,false,false); 
		else  if(nom_part=="proton")
		    image_puits_proton.dessine(grph,pos_part_int,1.,false,false); 

	    //else  if(nom_part=="quark"||nom_part=="antiquark")
	    //	dessineImage(grph,crop_quark,pos_part_int,diametre/2);
	    if(osc_harm_puits){
		if(nom_part=="meson"){
		    image_puits_meson.dessine(grph,puits_posit_fenetre,1.,false,false); 
		    image_puits_meson.dessine(grph,pos_part_int_labo,1.,false,false); 
		}
		if(nom_part=="proton"){
		    image_puits_proton.dessine(grph,puits_posit_fenetre,1.,false,false);
		    image_puits_proton.dessine(grph,pos_part_int_labo,1.,false,false); 
		    //puits_posit_fenetre.print(" ùùùùùùùùùpuits_posit_fenetre ");
		} 

		if(n_particules_initiales==1){
		    image_4_rouge.dessine(grph,puits_posit_fenetre,1.,false,false); 
		    image_4_rouge.dessine(grph,posit_cm_tot_int,1.,false,false);
		}
	    }
	    if(n_quark>1&&i_demarre==-1){
		toto_int.assigne(pos_centre);
		image_4_noire.dessine(grph,toto_int,1.,false,false);
	    } 
	}
	void dessine_part(boolean enregistrer){
	    for(int i=0;i<n_quark;i++){
		if(n_quark>1){
		    int k=i+1;if((n_quark>2)&&(k==n_quark))k=0;
		    if(k<n_quark)
		    	dessine_gluon_lie(quark[i],quark[k],nouvelle,i_demarre!=-1);
		}
		if(i_demarre==2){
		    if(particule_mere==-1||mere_deja_dessinee){
			//r_precedent.print("particule_mere=-1 j "+j+" r_precedent ");
			toto_int.assigne_int(r_precedent);
			//toto_int.print("particule_mere==-1 j "+j+"toto_int, ou plutot p_quark_int_mere");
			//quark[0].posit_q_int.print("j "+j+" nouvelle "+nouvelle+"chgt_coul "+chgt_coul+" quark[0].posit_q_int ");
			quark[0].dessine_q(nouvelle,quark[0].num_couleur,toto_int,false);		  
		    }else{
			int num_in=particule_mere;
			toto_int.assigne_int(part_comp[num_in].r_precedent);
			toto_int.print("particule_mere!=-1 num_part "+num_part+"toto_int, ou plutot p_quark_int_mere");
			quark[0].dessine_q(nouvelle||du_nouveau,part_comp[num_in].quark[0].num_couleur,toto_int,true);
			toto_int.print("num_part "+num_part+" particule_mere "+particule_mere+" r_precedent "+" num_couleur "+quark[0].num_couleur);
			mere_deja_dessinee=true;
			//part_comp[1123]=null;
		    }
		}else{
		    quark[i].dessine_q(nouvelle,-1,point_nul_int,false);
		    //quark[i].posit_q_int.print("j "+j+" i "+i+"quark[i].posit_q_int");
		}
		for(int jj=num_part+1;jj<n_particules_initiales;jj++){
		    if(i_demarre<2){
			for(int ii=0;ii<part_comp[jj].n_quark;ii++){
			    watf.assigne(quark[i].posit_q);
			    watf.soustrait(part_comp[jj].quark[ii].posit_q);
			    if(watf.longueur()<dist_lim_pot)
				dessine_gluon_lie(quark[i],part_comp[jj].quark[ii],nouvelle||part_comp[jj].nouvelle,i_demarre!=-1);
			}
		    }else{
			watf.assigne(pos_part);
			watf.soustrait(part_comp[jj].pos_part);
			boolean couple=quark[0].partenaire==jj&&part_comp[jj].quark[0].partenaire==num_part;
			//watf.print("watf.longueur() "+watf.longueur()+" watf ");
			if((watf.longueur()<dist_lim_pot)||couple)
			    dessine_gluon_lie(quark[0],part_comp[jj].quark[0],nouvelle||part_comp[jj].nouvelle,i_demarre!=-1);
		    }
		}
	    }
	    nouvelle=false;
	    quark[0].chgt_coul=false;
	}
	boolean mouvement_quarks(){
	    /*
	    e_init_dans_mouvement=energie_totale();
	    e_cin_init_dans_mouvement=energie_cin;
	    e_pot_init_dans_mouvement=energie_pot;
	    */
	    titi.assigne(point_nul);
	    for(int i=0;i<n_quark;i++)
		titi.additionne_facteur(quark[i].posit_q,1./3.);
	    for(int i=0;i<n_quark;i++){
		for(int j=i+1;j<n_quark;j++){
		    dist_q.assigne_soustrait(quark[i].posit_q,quark[j].posit_q);
		    if (dist_q.longueur_carre()>=dist_lim_pot*dist_lim_pot){ 
			//double a=energie_totale();
			double ee;double sm=quark[i].masse+quark[j].masse;
			double d_cm[]=new double[3];
			int ii=0;
			toto.assigne_facteur(quark[j].posit_q,quark[j].masse/sm);
			r_cm.assigne_facteur(quark[i].posit_q,quark[i].masse/sm);
			r_cm.additionne(toto);
			toto.assigne_facteur(quark[j].vit_q,quark[j].masse/sm);
			v_cm.assigne_facteur(quark[i].vit_q,quark[i].masse/sm);
			v_cm.additionne(toto);
			for(int k=0;k<2;k++){
			    if(k==0)
				ii=i;
			    else
				ii=j;
			    ri_cm[ii].assigne_soustrait(quark[ii].posit_q,r_cm);   
			    vi_cm[ii].assigne_soustrait(quark[ii].vit_q,v_cm);   
			    d_cm[ii]=ri_cm[ii].longueur();
			}
			double d_cm_tot=d_cm[i]+d_cm[j];
			double fact=(dist_lim_pot-0.1)/d_cm_tot;
			if(i_demarre!=-1)
			    fact*=0.9;
			/*
			System.out.println(" d_cm_tot avant "+(float)d_cm_tot+" dist_q.longueur() "+(float)dist_q.longueur()+" fact "+(float)fact);
			v_cm.print("i "+i+" j "+j+" v_cm ");
			if(v_cm.longueur()>3000.)
			    ri_cm[10].print("toto");
			if(i_demarre!=-1){
			    pos_part.print(" num_part "+num_part+" part_comp[1-num_part].pos_part.x "+(float)part_comp[1-num_part].pos_part.x+"pos_part_int_labo.x "+pos_part_int_labo.x+" pos_part ");
			    vit_part.print(" num_part "+num_part+" part_comp[1-num_part].vit_part.x "+(float)part_comp[1-num_part].vit_part.x+" x_du_cdm "+(float)x_du_cdm+" v_du_cdm.x "+(float)v_du_cdm.x+" vit_part ");
			}
			    titi.print(" titi ");
			*/
			for(int k=0;k<2;k++){
			    if(k==0)
				ii=i;
			    else
				ii=j;
			    ri_cm[ii].multiplie_cst(fact);
			    //quark[ii].posit_q.print(" avant ii "+ii+" quark[ii].posit_q ");
			    quark[ii].posit_q.assigne_additionne(ri_cm[ii],r_cm);
			    quark[ii].posit_q.print(" apres ii "+ii+" quark[ii].posit_q ");
			    quark[ii].vit_q.print(" ii "+ii+"avant quark[ii].vit_q  ");
			    //vi_cm[ii].print(" ii "+ii+" vi_cm[ii] ");
			    toto.assigne(ri_cm[ii]);
			    double scal=toto.scalaire(vi_cm[ii]);
			    toto.multiplie_cst(2.*scal/ri_cm[ii].longueur_carre());
			    //toto.print("scal "+scal+" ri_cm[ii].longueur() "+ri_cm[ii].longueur()+" toto");
			    quark[ii].vit_q.soustrait(toto); 
			    quark[ii].vit_q.print(" ii "+ii+" quark[ii].vit_q apres ");
			    
			    d_cm[ii]=ri_cm[ii].longueur();
			}
			d_cm_tot=d_cm[i]+d_cm[j];
			System.out.println(" d_cm_tot apres "+(float)d_cm_tot+" d_cm[i] "+(float)d_cm[i]+" d_cm[j] "+(float)d_cm[j]);
			//if(!melange&&!(i_demarre==0||i_demarre==1)){
			if(!melange){
			    double e_tot=energie_totale();
			    double de=e_tot-energie_part;
			    System.out.println("energie_totale() "+e_tot+" energie_part "+energie_part);
			    double dv=0.;
			    for(int k=0;k<2;k++){
				if(k==0)
				    ii=i;
				else
				    ii=j;
				double v2=quark[ii].vit_q.longueur_carre();			    
				double dv2=-2.*de/(quark[ii].masse*2.);
				quark[ii].vit_q.multiplie_cst(Math.sqrt(Math.abs(v2+dv2))/quark[ii].vit_q.longueur());
				if(v2+dv2<0)
				    quark[ii].vit_q.multiplie_cst(-1.);
				quark[ii].vit_q.print(" ii "+"quark[ii].vit_q ");
			    }
			    if(i_demarre==-1){
				e_tot=energie_totale();
				posit_cm_quarks.zero();
				for(int ij=0;ij<n_quark;ij++)
				    posit_cm_quarks.additionne_facteur(quark[ij].posit_q,quark[ij].masse/masse_des_quarks);
				posit_cm_quarks.print(" posit_cm_quarks ");
				posit_cm_quarks.soustrait(posit_cm_quarks0);
				posit_cm_quarks.print(" apres soustraction posit_cm_quarks ");
				for(int ij=0;ij<n_quark;ij++){
				    quark[ij].posit_q.soustrait(posit_cm_quarks);
				    quark[ij].posit_q.print("ij "+ij+" quark[ij].posit_q ");
				}

				vit_cm.zero();
				for(int ij=0;ij<n_quark;ij++)
				    vit_cm.additionne_facteur(quark[ij].vit_q,quark[ij].masse/masse_des_quarks);
				for(int ij=0;ij<n_quark;ij++){
				    quark[ij].vit_q.soustrait(vit_cm);
				    quark[ij].vit_q.print("ij "+ij+" quark[ij].vit_q ");
				}

			    }
			    /*
			    System.out.println(" e_init_dans_mouvement "+(float)e_init_dans_mouvement+" e_cin_init_dans_mouvement "+(float)e_cin_init_dans_mouvement+" e_pot_init_dans_mouvement "+(float)e_pot_init_dans_mouvement);
			    coco=energie_totale();
			    cici=energie_cin;
			    cucu=energie_pot;
			    posit_cm_quarks.print(" coco "+(float)coco+" cici "+(float)cici+" cucu "+(float)cucu+" posit_cm_quarks ");
			    */
			}
			return true;
		    }
		}
	    }
	    return false;
	}
	
	public void bouge(){    
	    if(i_print<-1){
		pos_part.print("pos_part deb_bouge");
		vit_part.print(" vit_part deb bouge");
		for(int jj=0;jj<n_quark;jj++)
		    quark[jj].vit_q.print("vit_q des quarks ");
	    }
	    //System.out.println(" melange "+melange);
	    if(quantique){
		jjk++;if(jjk==50)jjk=1;
		toto.zero();	    
		if(jjk==1){
		    for(int i=0;i<n_quark;i++){
			if(i!=n_quark-1){
			    quark[i].posit_q.assg_r_phi(diametre/2.*0.8*Math.sqrt(Math.random()),Math.random()*2.*pi-pi);
			    toto.additionne_facteur(quark[i].posit_q,quark[i].masse/quark[n_quark-1].masse);
			}
			else{ 
			    quark[i].posit_q.zero();quark[i].posit_q.soustrait(toto);
			}
			quark[i].posit_q.additionne(pos_part);	
			//if(i_print<100)quark[i].posit_q.print(" quark[i].posit_q i "+i);
			//i_print++;
		    }
		}
	    }else{
		reflexion=false;
		for(int i=0;i<n_quark;i++)
		    quark[i].deplace();
		if(osc_harm_puits&&reflexion&&!melange&&i_demarre==-1){
		    double de=energie_totale()-energie_part;
		    for(int i=0;i<n_quark;i++){
			point vii=new point(quark[i].vit_q);vii.soustrait(vit_part);
			double v=vii.longueur();
			//System.out.println("v "+v+" quark[i].vit_q.x "+quark[i].vit_q.x+" quark[i].vit_q.y "+quark[i].vit_q.y); 
			double dv=0.;
			if(v>0.){
			    dv=Math.abs(de)/(quark[i].masse*v*n_quark);
			    //else
			    //dv=Math.sqrt(Math.abs(de)*2./quark[i].masse_quark);	
			    if(de<0)
				vii.multiplie_cst(1.+dv/v);
			    else
				vii.multiplie_cst(1.-dv/v);
			    quark[i].vit_q.assigne_additionne(vii,vit_part);
			}
		    }
		}
		if(osc_harm_limite){
		    totologic=true;
		    while(totologic)
			totologic=mouvement_quarks();
		}
	    }
	    posit_cm_quarks.zero();
	    for(int i=0;i<n_quark;i++){
		//if(osc_harm_limite)
		//quark[i].posit_q.print("apres deplace i "+i+"quark[i].posit_q");
		posit_cm_quarks.additionne_facteur(quark[i].posit_q,quark[i].masse/masse_des_quarks);
	    }
	    //if(osc_harm_limite)
	    //posit_cm_quarks.print("num_part "+num_part+" reflexion "+reflexion+"posit_cm_quarks");
	    if(!melange){
		toto.assigne_facteur(vit_part,dt_fenetre);
		if(osc_harm_limite&&(!diffusion))
		    toto.additionne_soustrait(posit_cm_quarks,pos_part);
		//if(num_part==1)
		// System.out.println(" pas melange vit_part.x "+(float)vit_part.x+" pos_part_int_labo.x "+(float)pos_part_int_labo.x); 
		//toto.additionne(posit_cm_quarks);
		pos_part.additionne(toto);
		/*
		if(osc_harm_limite)//*$*$
		    for(int jk=0;jk<n_quark;jk++)
			quark[jk].posit_q.additionne(toto);
		*/
	    }else if(i_demarre==0||i_demarre==1){
		vit_part.additionne_facteur(force_part,dt_fenetre/masse);
		d_pos_part.assigne_facteur(vit_part,dt_fenetre);
		/*
		if(num_part==1)
		    System.out.println("dans bouge, avant, avec melange part_comp[0].pos_part.x "+(float)part_comp[0].pos_part.x+" pos_part.x "+(float)pos_part.x);
		*/
		pos_part.additionne(d_pos_part);
		/*
		if(num_part==1){
		    System.out.println("dans bouge avec melange part_comp[0].d_pos_part.x "+(float)part_comp[0].d_pos_part.x+" d_pos_part.x "+(float)d_pos_part.x);
		    System.out.println("dans bouge avec melange part_comp[0].pos_part.x "+(float)part_comp[0].pos_part.x+" pos_part.x "+(float)pos_part.x);
		}
		*/
		posit_cm_quarks0.additionne(d_pos_part);
		if(osc_harm_limite)//*$*$
		    for(int jk=0;jk<n_quark;jk++)
			quark[jk].posit_q.additionne(d_pos_part);
 		//pos_part.print(" apres pos_part ");	
	    }
	    //pos_part.print("num_part "+num_part+" pos_part ");
	    if(osc_harm_puits){
		//if(num_part==0)vitesse_puits.print(" vitesse_puits ");
		vitesse_puits.zero();
		if(i_demarre!=-1){
		    for(int jj=0;jj<n_quark;jj++)
		    toto.assigne(pos_part);
		    //toto.additionne(puits_posit);
		    puits_posit_fenetre.assigne(toto);
		    if(puits_posit.longueur_carre()>diametre*diametre/4)
  			part_comp[433]=null;
		}else{
		    for(int jj=0;jj<n_quark;jj++)
			vitesse_puits.additionne_facteur(quark[jj].vit_q,-quark[jj].masse/masse);
		    
		    dpuits_posit.assigne_facteur(vitesse_puits,dt_fenetre);
		    puits_posit.additionne(dpuits_posit);
		    toto.assigne_additionne(puits_posit,pos_centre);
		    puits_posit_fenetre.assigne(toto);
		    /*
		    calc_et_soustrait_pos_cm_tot();
		    corrige=false;
		    for(int i=0;i<n_quark;i++)
			corrige=corrige||quark[i].verifie_et_corrige(i);
		    if(corrige){
			posit_cm_quarks.zero();
			for(int i=0;i<n_quark;i++)
			    posit_cm_quarks.additionne_facteur(quark[i].posit_q,quark[i].masse/masse_des_quarks);
			calc_et_soustrait_pos_cm_tot();
		    }
		    posit_cm_tot.additionne(pos_part);posit_cm_tot_int.assigne(posit_cm_tot);
		    */
		}
	    }
	    //	    if(reflexion&&(i_print<10)||i_print>0)
	    if(i_print<-1){
		//i_print++;
		//System.out.println(" i_print "+i_print);
		pos_part.print("particule_compos.pos_part");
		vit_part.print("particule_initiale.vit_part ");
		//puits_posit.print(" puits_posit ");
		//vitesse_puits.print(" vitesse_puits ");
		//toto.print("particule_initiale.toto");
	    }

	    //	    pos_part.print("pos_part");
	    //posit_cm.print(" posit_cm_quarks ");
	    //newif(osc_harm_limite&&(!diffusion))
	    //new	pos_part.additionne(posit_cm_quarks);
	    //System.out.println("pos_part_int.x "+pos_part_int.x+" dpcx "+dpcx+" vit.x "+vit.x);
	    //quark[i].posit_q_fenetre.print("fin bouge i "+i+" quark[i].posit_q ");
	}
	public void calc_et_soustrait_pos_cm_tot(){
	    posit_cm_tot.assigne(posit_cm_quarks);posit_cm_tot.multiplie_cst(masse_des_quarks);
	    posit_cm_tot.additionne_facteur(puits_posit,masse-masse_des_quarks);
	    posit_cm_tot.multiplie_cst(1./masse);
	    for(int i=0;i<n_quark;i++)
		quark[i].posit_q.soustrait(posit_cm_tot);
	    puits_posit.soustrait(posit_cm_tot);
	}
	public double energie_totale1(){
	    double a=0.;double b=0.;double c;
	    for(int i=0;i<n_quark;i++){
		point vii=new point(quark[i].vit_q);vii.soustrait(vit_part);
		a+=0.5*masse_quark*vii.longueur_carre();
		for(int j=i+1;j<n_quark;j++){
			point dd=new point(0,0);dd.assigne(quark[i].posit_q);dd.soustrait(quark[j].posit_q);
			b+=0.5*raideur*dd.longueur_carre();
			System.out.println("b "+b+" i "+i+"j "+j);
		    }
		c=a+b;
		//System.out.println("quark[i].posit_q.x "+quark[i].posit_q.x+" quark[i].posit_q.y "+quark[i].posit_q.y );
		//System.out.println("a "+a+" b "+b+" c "+c+" i "+i);
	    }
	    c=a+b;
	    return (a+b);
	}
	public double energie_totale(){
	    energie_cin=0.;energie_pot=0.;
	    for(int i=0;i<n_quark;i++){
		point vii=new point(quark[i].vit_q);vii.soustrait(vit_part);
		energie_cin+=0.5*masse_quark*vii.longueur_carre();
		for(int j=i+1;j<n_quark;j++){
		    toto.assigne_soustrait(quark[i].posit_q,quark[j].posit_q);
		    energie_pot+=0.5*raideur*toto.longueur_carre();
		}
	    }
	    double ab=energie_cin+energie_pot;
	    if(num_fen==-1)
		System.out.println(" energie_cin "+(float)energie_cin+" energie_pot "+(float)energie_pot+" ab "+(float)ab+" energie_init "+(float)energie_init);

	    double aa=0.;
	    if(osc_harm_puits){
		aa=0.5*(masse-masse_des_quarks)*vitesse_puits.longueur_carre();
		if(n_particules_initiales==1){
		    if(energie_cin+aa+energie_pot>1.e4)
			for(int i=0;i<n_quark;i++)
			    quark[i].posit_q.print("quark "+i+" posit_q");
		}
	    }
	    return (energie_cin+aa+energie_pot);
	}
	public void zero_forces(){
	    for(int i=0;i<n_quark;i++)
		quark[i].force.zero();
	    force_part.zero();
	}
	public void forces(){
	    for(int i=0;i<n_quark;i++){
		for(int j=i+1;j<n_quark;j++){
		    toto.assigne_soustrait(quark[i].posit_q,quark[j].posit_q);
		    quark[i].force.additionne_facteur(toto,-raideur);
		    quark[j].force.additionne_facteur(toto,raideur);
		}
	    }
	}

	class quark{
	    int diam_quark; double masse;fenetre_particules_initiales called;
	    point posit_q,posit_garde,vit_q,vit_garde,vit_p,posit_initial,vit_initiale;
	    point_int posit_q_int,posit_q_int_labo;boolean hadronise=false;
	    point force,force_p;boolean debut_calculs_forces=true;
	    dernier_parcours letzt;
	    point distance,vv,r_cercle,unite,dposit;double distance_car=0.;
	    int num_q,partenaire;int num_couleur;
            double dx,dy;
	    boolean anti_q,deja_pere,chgt_coul=false;boolean initial=false;
	    public quark(point rr,point vvv,double mass,int diam_quark1,int num_couleur1,boolean anti_q1,int num_q1){
		//		posit_q.print("num_q1 "+num_q1+" posit_q ");
		//vit_q.print("num_q1 "+num_q1+" vit_q ");

		posit_q=new point(rr.x,rr.y);
		if(i_demarre==-1){
		    posit_q.additionne(pos_centre);
		    /*
		    if(osc_harm_puits){
			puits_posit.print(" puits_posit ");
			puits_posit_fenetre.print(" puits_posit_fenetre ");
			posit_q.print("num_q1 "+num_q+" posit_q1 ");
		    }
		    */
		}
		vit_q=new point(vvv.x,vvv.y);
		if(i_demarre==0||i_demarre==1){
		    posit_q.additionne(pos_part);
		    vit_q.additionne(vit_part);
		}
		posit_garde=new point(posit_q);
		posit_initial=new point(posit_q.x,posit_q.y);
		vit_initiale=new point(vit_q.x,vit_q.y);
		vit_p=new point(vit_q.x,vit_q.y);vit_garde=new point(vit_q.x,vit_q.y);
		num_q=num_q1;partenaire=-1;deja_pere=false;
		force=new point(0,0);
		force_p=new point(0,0);
		posit_q_int=new point_int(0,0);
		posit_q_int_labo=new point_int(0,0);
		masse=mass;diam_quark=diam_quark1;
		num_couleur=num_couleur1;
		//System.out.println("num_couleur "+num_couleur);
		anti_q=anti_q1;
		distance=new point(0,0);
		r_cercle=new point(0,0);
		unite=new point(0,0);
		dposit=new point(0,0);
		letzt=new dernier_parcours();
		//posit_q.print(" posit_q quark "+posit_q);vit_q.print(" vit_q quark "+vit_q);
	    }
	    void decale_quark_pedago(point_int[] pstns_qrks_pdg,int n_qrks_pdg,point_int pqi){
		pstns_qrks_pdg[n_qrks_pdg].assigne_int(pqi);
		for(int i=0;i<n_qrks_pdg;i++){
		    tutu_int.assigne_soustrait(pqi,pstns_qrks_pdg[i]);
		    if(tutu_int.longueur_carre()<Math.pow(diametre_quarks/2,2))
			pstns_qrks_pdg[n_qrks_pdg].y=pstns_qrks_pdg[i].y+diametre_quarks/2;
		}
		posit_q_int.assigne_int(pstns_qrks_pdg[n_qrks_pdg]);
		pos_part_int.assigne_int(pstns_qrks_pdg[n_qrks_pdg]);
		/*
		if(osc_harm_limite){
		    pqi.print(" pqi ");
		    pstns_qrks_pdg[n_qrks_pdg].print("n_qrks_pdg "+n_qrks_pdg+" pstns_qrks_pdg[n_qrks_pdg]");   
		}
		*/
		pos_part.assigne_int(pstns_qrks_pdg[n_qrks_pdg]);
		pos_part.multiplie_cst(1.);
	    }
	    void dessine_quark_pedago(boolean neuve,int num_coul_mere,point_int p_quark_int_mere,boolean avec_maman){
		//p_quark_int.print("££££p_quark_int ");
		part_comp[num_part].a_ete_dessinee=true;
		if(neuve){
		    decale_quark_pedago(positions_quarks_pedago,n_quarks_pedago,posit_q_int);
		    decale_quark_pedago(positions_quarks_pedago,n_quarks_pedago,positions_quarks_pedago[n_quarks_pedago]);
		}else
		    positions_quarks_pedago[n_quarks_pedago].assigne_int(posit_q_int);    
		//positions_quarks_pedago[n_quarks_pedago].print("n_quarks_pedago "+n_quarks_pedago+" positions_quarks_pedago[n_quarks_pedago] ");
		//decale_quark_pedago(positions_quarks_mere_pedago,n_quarks_mere_pedago,p_quark_int_mere);
		point_int pp_i=new point_int(positions_quarks_pedago[n_quarks_pedago]);
		if(cdm)
		    pp_i.y-=(ordonnee_initiale-50);
		for(int i=indice_pedago;i<nb_images_pedago;i++){
		    image_quark[num_couleur].dessine(resume_int.gTampon[i],pp_i,fact_zoom,true,anti_q);
		    if(chgt_coul)
			image_quark[num_couleur].dessine(resume_int.gTampon[i],p_quark_int_mere,fact_zoom,true,anti_q);
		    if(neuve){
			resume_int.gTampon[i].setColor(coul[num_coul_mere].col);
			point_int pp_j=new point_int(p_quark_int_mere);
			if(cdm)
			    pp_j.y-=(ordonnee_initiale-50);
			if(avec_maman){
			    resume_int.gTampon[i].setColor(coul[num_coul_mere].col);
			    if(!cdm)//%%
				resume_int.gTampon[i].drawLine((int)(pp_i.x*fact_zoom),(int)(pp_i.y*fact_zoom)-50,(int)(pp_j.x*fact_zoom),(int)(pp_j.y*fact_zoom)-50);//%%
			    else//%%
				resume_int.gTampon[i].drawLine((int)(pp_i.x*fact_zoom),pp_i.y,(int)(pp_j.x*fact_zoom),pp_j.y);
			    //pp_i.print(" num_part "+ num_part+" avec_maman "+avec_maman+" p_quark_int_mere,pp_i ");
			}
		    }else{
			resume_int.gTampon[i].setColor(coul[part_comp[num_part].quark[0].num_couleur].col);
			part_comp[num_part].quark[0].letzt.dessine(resume_int.gTampon[i]);
			//System.out.println(" letzt.dessine num_part "+ num_part+ " part_comp[num_part].quark[0].letzt.nb_pts "+part_comp[num_part].quark[0].letzt.nb_pts);
			//if(part_comp[num_part].quark[0].letzt.nb_pts>1)		
			//part_comp[888]=null;
		    }
		}
		part_comp[num_part].r_dernier.assigne_int(positions_quarks_pedago[n_quarks_pedago]);
		//part_comp[num_part].r_dernier.assigne_int(positions_quarks_pedago[n_quarks_pedago]);
		//if(cdm)
		//part_comp[num_part].r_dernier.x+=(int)(abscisse_initiale);  
		//part_comp[num_part].r_dernier.multiplie_cst((int)(1.+0.01));
		
		n_quarks_pedago++;
	    }
	    void dessine_q(boolean neuve,int num_coul_mere,point_int p_quark_int_mere,boolean avec_maman){
		image_quark[num_couleur].dessine(grph,posit_q_int,1.,false,anti_q);
		if(i_demarre==0||i_demarre==1)
		    image_quark[num_couleur].dessine(grph,posit_q_int_labo,1.,false,anti_q);
		if(i_demarre==2){
		    //System.out.println("num_part "+num_part+" neuve "+neuve+" chgt_coul "+chgt_coul+"du_nouveau "+du_nouveau);
		    if(du_nouveau&&!relire_l_evenement){
			//System.out.println(" num_part "+num_part+" part_comp[num_part].quark[0].letzt.nb_pts "+part_comp[num_part].quark[0].letzt.nb_pts);
			dessine_quark_pedago(neuve,num_coul_mere,p_quark_int_mere,avec_maman);
			part_comp[num_part].quark[0].letzt.nb_pts=0; 
			if(posit_q_int.y>p_quark_maxy)
			    p_quark_maxy=posit_q_int.y;
			if(posit_q_int.y<p_quark_miny)
			    p_quark_miny=posit_q_int.y;
		    }
		    if(num_part!=-1){
			point_int pp_i=new point_int(posit_q_int);
			if(cdm)
			    pp_i.y-=(ordonnee_initiale-50);
			part_comp[num_part].quark[0].letzt.incremente(pp_i);
			//pp_i.print("fin dessine_quark num_part "+num_part+" part_comp[num_part].quark[0].letzt.nb_pts "+part_comp[num_part].quark[0].letzt.nb_pts+" pp_i ");
		    }
		}
	    }
	    public void remise_a_zero(){
		posit_q.assigne(posit_initial);
		vit_q.assigne(vit_initiale);
	    }
	    public boolean verifie_et_corrige(int i_q){
		boolean ccc=false;
		point ppp=new point(posit_q);ppp.soustrait(pos_part);
		distance.assigne_soustrait(ppp,puits_posit);
		if(distance.longueur()>diametre/2.){
		    double facteur=(distance.longueur()-diametre/2.)/diametre/2.;
		    ppp.additionne_facteur(distance,-facteur);
		    posit_q.assigne_additionne(ppp,pos_part);
		    point vvv=new point(vit_q);vvv.soustrait(vit_part);
		    double vitscal=vvv.scalaire(distance);
		    ccc=true;
		    //System.out.println(" correction, facteur "+facteur+" vitscal "+vitscal+" i_q "+i_q);
		    if(vitscal>0){
			vvv.multiplie_cst(-1.);
			vit_q.assigne_additionne(vvv,vit_part);
			vitesse_puits.zero();
			for(int jj=0;jj<n_quark;jj++)
			    vitesse_puits.additionne_facteur(quark[jj].vit_q,-quark[jj].masse/masse);
			if(facteur>0.001){
			    double vv_ii=vit_q.scalaire(distance);
			    posit_q.print("i_q "+i_q+" posit_q ");
			    puits_posit.print("vv_ii "+vv_ii+" puits_posit ");
			}
			if(facteur>0.01)
			    dispose();
		    }
		    if(facteur>1.01)
			dispose();
		}
		return ccc;
	    }
	    public void deplace(){		
		if(debut_calculs_forces){
		    force_p.assigne(force);
		    vit_p.assigne(vit_q);
		    debut_calculs_forces=false;
		}
		vit_q.additionne_facteur(force,dt_fenetre/masse);
		dposit.assigne_facteur(vit_q,dt_fenetre);
		posit_q.additionne(dposit);
		force_p.assigne(force);
		vit_p.assigne(vit_q);
		if(osc_harm_puits){	
		    point ppp=new point(posit_q);
		    if(i_demarre!=-1)
			ppp.soustrait(pos_part);
		    else if(osc_harm_puits){
			ppp.soustrait(pos_centre);
			ppp.soustrait(puits_posit);
		    }
		    point vvv=new point(vit_q);vvv.soustrait(vit_part);
		    double r=diametre/2.;
		    //puits_posit.print(" puits_posit "); 
		    //distance.print(" distance ");
		    if (ppp.longueur_carre()>r*r){ 
			reflexion=true;a_reflechi=true;
			point vun=new point(dposit);
			vun.multiplie_cst(1./vun.longueur());
			//en fait vvscal devrait etre positif
			r_cercle.assigne(ppp);
			r_cercle.multiplie_cst(0.999*r/r_cercle.longueur());
			unite.assigne(r_cercle);unite.multiplie_cst(1./r_cercle.longueur());
    			double co=vvv.scalaire(unite);
			if(co>0)
			    vvv.additionne_facteur(unite,-2.*co);
				//System.out.println("vit_qesse "+vit.longueur()+" vitesse_p "+vit_p.longueur());
			ppp.assigne(r_cercle);
			if(i_demarre!=-1)
			    posit_q.assigne_additionne(ppp,pos_part);
			else{
			    posit_q.assigne_additionne(ppp,pos_centre);
			    posit_q.additionne(puits_posit);
			}
			vit_q.assigne_additionne(vvv,vit_part);
			vit_p.assigne(vit_q);
			if(r_cercle.longueur()>r*1.01){
			    double vv_iqqq=vit_q.scalaire(unite);
			    dposit.print(" alerte!!!!! num_q "+ num_q+" dposit ");
			    posit_q.print("vv_iqqq "+vv_iqqq+" posit_q ");
			    r_cercle.print(" r_cercle.longueur() "+r_cercle.longueur()+"r_cercle ");
			    vit_q.print(" vit_q ");
			    puits_posit.print("puits_posit ");
			    part_comp[432]=null;
			    fin_event_naturelle=true;
			}
			//dispose();
		    }
		}
	    }
	    public class dernier_parcours{
		point_int pt[]=new point_int[500];
		int nb_pts=0;
		public dernier_parcours(){
		    pt[0]=new point_int(r_precedent);
		    for (int i=1;i<500;i++){
			pt[i]=new point_int(point_nul_int);
		    }
		}
		void incremente(point_int ppt){
		    if(nb_pts==0)
			pt[nb_pts++].assigne_int(ppt);
		    else 
			if(nb_pts<499)
			    if(!ppt.egale(pt[nb_pts-1]))
				pt[nb_pts++].assigne_int(ppt);
		}
		void dessine(Graphics g){
		    g.setColor(coul[num_couleur].col);
		    for (int i=0;i<nb_pts;i++){
			if(!cdm)//%%
			    g.drawLine((int)(pt[i].x*fact_zoom),(int)(pt[i].y*fact_zoom)-50,(int)(pt[i].x*fact_zoom),(int)(pt[i].y*fact_zoom)-50);
			else//%%
			    g.drawLine((int)(pt[i].x*fact_zoom),pt[i].y,(int)(pt[i].x*fact_zoom),pt[i].y);//%%
			//pt[i].print(" i "+i+" pt ");
		    }
		}
	    }
	}
    }
    class ligne_coloree{
	int num_col;point_int p1,p2;
	ligne_coloree(int num_col1,point_int pa,point_int pb){
	    num_col=num_col1;
	    p1=new point_int(pa);
	    p2=new point_int(pb);  
	}
	void assigne(int num_col1,point_int pa,point_int pb){
	    num_col=num_col1;p1.assigne_int(pa);p2.assigne_int(pb);  
	}
	void assigne(ligne_coloree l){
	    num_col=l.num_col;p1.assigne_int(l.p1);p2.assigne_int(l.p2);  
	}
	boolean egale(ligne_coloree l){
	    return num_col==l.num_col&&p1.egale(l.p1)&&p2.egale(l.p2);  
	}
	void dessine(Graphics gg){
	    gg.setColor(coul[num_col].col);
	    if(!cdm)//%%
		gg.drawLine((int)(p1.x*fact_zoom),(int)(p1.y*fact_zoom)-50,(int)(p2.x*fact_zoom),(int)(p2.y*fact_zoom)-50);//%%
	    else//%%
		gg.drawLine((int)(p1.x*fact_zoom),p1.y,(int)(p2.x*fact_zoom),p2.y);
	    //pa.print("hadrons  pa ");
	    //pb.print("hadrons  pb ");
	}
	void print(String s){
	    System.out.println(s+" num_col "+num_col);
	    p1.print(" p1 ");
	    p2.print(" p2 ");
	}	
    }
    class couleur {
	Color col,col_pale;
	int r,v,b,r_pale,v_pale,b_pale;int n_c,n_c_cpl1,n_c_cpl2;

	public couleur (int n_c1){
	    n_c=n_c1;
	    if(n_c==0){
		r=255;v=0;b=0;
		r_pale=255;v_pale=111;b_pale=111;
	    }
	    if(n_c==1){
		r=0;v=255;b=0;
		r_pale=111;v_pale=255;b_pale=111;
	    }
	    if(n_c==2){
		r=0;v=0;b=255;
		r_pale=111;v_pale=111;b_pale=255;
	    }
	    col=new Color(r,v,b);
	    col_pale=new Color(r_pale,v_pale,b_pale);
	    /*
	    crop_quark=createImage(diametre_quarks,diametre_quarks);
	    gTampon_quark[n_c]=crop_quark.getGraphics();
	    gTampon_quark[n_c].setColor(col);
	    System.out.println("n_c "+n_c+" diametre_quarks "+diametre_quarks+" col "+col);
	    gTampon_quark[n_c].fillOval(0,0,diametre_quark,diametre_quark);
	    */
	    couleurs_compl();
	}
	void couleurs_compl(){
	    n_c_cpl1=n_c+1;
	    if(n_c_cpl1==3)
		n_c_cpl1=0;
	    n_c_cpl2=n_c-1;
	    if(n_c_cpl2==-1)
		n_c_cpl2=2;
	}
    }
}
class quadrimoment{
    double energie,e_pot,e_cin,m=0.; point moment;
    public quadrimoment(double m1,double e_pot1,point vit){
	m=m1;
	e_pot=e_pot1;
	e_cin=0.5*m*vit.longueur_carre();
	energie=e_pot+e_cin;	
	moment=new point(vit);
	moment.multiplie_cst(m);
    }
    public void assigne(double m1,double e_pot1,point vit){
	m=m1;
	e_pot=e_pot1;
	e_cin=0.5*m*vit.longueur_carre();
	energie=e_pot+e_cin;	
	moment.assigne(vit);moment.multiplie_cst(m);
    }
    public void assigne(quadrimoment q){
	energie=q.energie;
	moment.assigne(q.moment);
	e_pot=q.e_pot;
	e_cin=q.e_cin;
     }
    public void assigne_additionne(quadrimoment q,quadrimoment qq){
	energie=q.energie+qq.energie;
	moment.assigne_additionne(q.moment,qq.moment);
	e_pot=q.e_pot+qq.e_pot;
	e_cin=q.e_cin+qq.e_cin;
     }
    public void additionne(quadrimoment q){
	energie+=q.energie;
	e_pot+=q.e_pot;
	e_cin+=q.e_cin;
	moment.additionne(q.moment);
    }
    public void soustrait(quadrimoment q){
	energie-=q.energie;
	e_pot-=q.e_pot;
	e_cin-=q.e_cin;
	moment.soustrait(q.moment);
    }
    public void zero(){
	m=0.;
	energie=0;
	e_pot=0.;
	e_cin=0.;
	moment.zero();
    }
    public void print(String st){
	System.out.println(st+ " energie "+(float)energie+ " e_pot "+(float)e_pot+ " e_cin "+(float)e_cin);
	moment.print(" masse "+(float)m+" moment ");
    }
}
class point {
    double x,y;
    
    public point(int xi, int yi){
	x=(double)xi;y=(double)yi;
    }
    public point(double xi, double yi){
	x=xi;y=yi;
    }
    public point(point v){
	x=v.x;y=v.y;
    }
    public point(point_int v){
	x=(double)v.x;y=(double)v.y;
    }
    public double somme_comp(){
	return (x+y);
    }
    public double scalaire(point c){
	return(x*c.x+y*c.y);
    }
    public double longueur(){
	double d=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	return(d);
    }
    public double longueur_carre(){
	double d=Math.pow(x,2)+Math.pow(y,2);
	return(d);
    }
    public void zero(){
	x=0.;y=0.;
    }
    public void print(String st){
	System.out.println(st+ " x "+(float)x+" y "+(float)y);
    }
    public void rand(double anb){
	x=(0.5-Math.random())*anb;
	y=(0.5-Math.random())*anb;
    }
    public void rotation(double angle){
	double cos=Math.cos(angle);double sin=Math.sin(angle);
	double x_p=x;double y_p=y;
	x=cos*x_p-sin*y_p;
	y=sin*x_p+cos*y_p;
    }
    public void assg_r_phi(double r, double phi){
	x=r*Math.cos(phi);y=r*Math.sin(phi);
    }
    public void assigne(point ccc){
	x=ccc.x;y=ccc.y;
    }
    public void assigne_int(point_int ccc){
	x=(double)ccc.x;y=(double)ccc.y;
    }
    public void assigne(double a,double b){
	x=a;y=b;
    }
    public void assigne_additionne(point ccc,point ddd){
	x=ccc.x+ddd.x;y=ccc.y+ddd.y;
    }
    public void assigne_soustrait(point ccc,point ddd){
	x=ccc.x-ddd.x;y=ccc.y-ddd.y;
    }
    public void assigne_facteur(point a,double b){
	x=a.x*b;y=a.y*b;
    }
    public void assigne_diviseur(point a,double b){
	x=a.x/b;y=a.y/b;
    }
    public void soustrait(point a){
	x-=a.x;y-=a.y;
    }
    public void soustrait_int(point_int a){
	x-=a.x;y-=a.y;
    }
    public void additionne(point a){
	x+=a.x;y+=a.y;
    }
    public void additionne_soustrait(point a,point b){
	x+=a.x;y+=a.y;x-=b.x;y-=b.y;
    }
    public double produit(point a){
	return(x*a.y-y*a.x);
    }
    public void soustrait_facteur(point a,double c){
	x-=c*a.x;y-=c*a.y;
    }
    public void soustrait_diviseur(point a,double c){
	x-=a.x/c;y-=a.y/c;
    }
    public void additionne_facteur(point a,double c)	{
	x+=c*a.x;y+=c*a.y;
    }
    public void additionne_diviseur(point a,double c)	{
	x+=a.x/c;y+=a.y/c;
    }
    public void multiplie_cst(double a){
	x*=a;y*=a;
    }
}
class point_int{
    int x,y;
    public point_int(int xi, int yi){
	x=xi;y=yi;
    } 
    public point_int(double xi,double yi){
	x=(int)Math.round(xi);y=(int)Math.round(yi);
    } 
    public point_int(point a){
	x=(int)Math.round(a.x);y=(int)Math.round(a.y);
    } 
    public point_int(point_int a){
	x=a.x;y=a.y;
    } 
    public double longueur(){
	double d=Math.sqrt(Math.pow((double)x,2)+Math.pow((double)y,2));
	return(d);
    }
    public boolean egale(point_int a){
	return x==a.x&&y==a.y;
    }
    public double longueur_carre(){
	return x*x+y*y;
    }
    public void print(String st){
	System.out.println(st+ " x "+x+" y "+y);
    }
    public void assigne(point a){
	x=(int)Math.round(a.x);y=(int)Math.round(a.y);
    }
    public void assigne_soustrait(point_int a,point_int b){
	x=a.x-b.x;y=a.y-b.y;
    }
    public void assigne_int(point_int a){
	x=a.x;y=a.y;
    }
    public void assigne_int(int a,int b){
	x=a;y=b;
    }
    public void additionne(point_int a){
	x+=a.x;y+=a.y;
    }
    public void soustrait(point_int a){
	x-=a.x;
	y-=a.y;
    }
    public void assigne_facteur(point a,double b){
	x=(int)Math.round(a.x*b);y=(int)Math.round(a.y*b);
    }
    public void assigne_facteur(point_int a,double b){
	x=(int)(a.x*b);y=(int)(a.y*b);
    }
    public void assigne_diviseur(point_int a,int b){
	x=(a.x/b);y=(a.y/b);
    }
    public void assigne_diviseur_sur_y(point_int a,int b){
	x=a.x;y=a.y/b;
    }
    public void multiplie_cst(double a){
	x=(int)Math.round(a*x);y=(int)Math.round(a*y);
    }
}










































