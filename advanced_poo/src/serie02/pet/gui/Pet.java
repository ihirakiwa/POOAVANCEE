package serie02.pet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import serie02.pet.model.PetModel;
import serie02.pet.model.StdPetModel;

public class Pet {
    
    private final JFrame frame;
    private final JLabel statusBar;
    private final JTextArea editor;
    private final JScrollPane scroller;
    private final PetModel model;
    private final Map<Item, JMenuItem> menuItems;

    // CONSTRUCTEUR
    
    public Pet() {
        // MODELE
        model = new StdPetModel();
        // VUE
        frame = buildMainFrame();
        editor = buildEditor();
        scroller = new JScrollPane();
        statusBar = new JLabel();
        menuItems = buildMenuItemsMap();
        placeMenuItemsAndMenus();
        placeComponents();
        // CONTROLEUR
        connectControllers();
    }
    
    //COMMANDE
    
    public void display() {
        setItemsEnabledState();
        updateStatusBar();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS
    
    private JFrame buildMainFrame() {
        final Dimension prefSize = new Dimension(640, 480);
        
        JFrame jf = new JFrame("Petit Éditeur de Texte");
        jf.setPreferredSize(prefSize);
        return jf;
    }
    
    private JTextArea buildEditor() {
        final int fontSize = 14;
        
        JTextArea jta = new JTextArea();
        jta.setBackground(Color.BLACK);
        jta.setForeground(Color.LIGHT_GRAY);
        jta.setCaretColor(Color.RED);
        jta.setFont(new Font("Courier New", Font.PLAIN, fontSize));
        return jta;
    }
    
    /**
     * Création de la correspondance Item -> JMenuItem.
     */
    private Map<Item, JMenuItem> buildMenuItemsMap() {
        /*****************/
        /** A COMPLETER **/
    	
    	Map<Item, JMenuItem> map = new EnumMap<Item, JMenuItem>(Item.class);
    	for(Item i : Item.values()) {
    		JMenuItem jmi = new JMenuItem(i.label());
    		map.put(i, jmi);
    	}
    	return map;
        /*****************/
    }
    
    /**
     * Place les menus et les éléments de menu sur une barre de menus, et cette
     *  barre de menus sur la fenêtre principale.
     */
    private void placeMenuItemsAndMenus() {
        /*****************/
        /** A COMPLETER **/
    	JMenuBar jmb = new JMenuBar();
    	for(Menu m : Menu.STRUCT.keySet()){
    		JMenu jm = new JMenu(m.label());
    		for(Item i: Menu.STRUCT.get(m)) {
    			if(i == null) {
    				jm.add(new JSeparator());
    			}else {
    				jm.add(new JMenuItem(i.label()));
    			}
    			
    		}
    		jmb.add(jm);
    	}
        /*****************/
    	frame.setJMenuBar(jmb);
    }
    
    private void placeComponents() {
        frame.add(scroller, BorderLayout.CENTER);
        
        JPanel p = new JPanel(new GridLayout(1, 0));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        { //--
            p.add(statusBar);
        } //--
        
        frame.add(p, BorderLayout.SOUTH);
    }
    
    private void connectControllers() {
        /*
         * L'opération de fermeture par défaut ne doit rien faire car on se
         *  charge de tout dans un écouteur qui demande confirmation puis
         *  libère les ressources de la fenêtre en cas de réponse positive.
         */
        /*****************/
        /** A COMPLETER **/
        /*****************/
        
        /*
         * Observateur du modèle.
         */
        /*****************/
        /** A COMPLETER **/
        /*****************/
        
        /*
         * Écouteurs des items du menu
         */
        menuItems.get(Item.NEW).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.NEW_FROM_FILE).addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*****************/
                    /** A COMPLETER **/
                    /*****************/
                }
            }
        );
        menuItems.get(Item.OPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.REOPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.SAVE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.SAVE_AS).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.CLOSE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.CLEAR).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.QUIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
    }
    
    /**
     * Gère l'état de la disponibilité des éléments du menu en fonction de
     *  l'état du modèle.
     */
    private void setItemsEnabledState() {
        /*****************/
        /** A COMPLETER **/
    	menuItems.get(Item.CLEAR).setEnabled(model.getDocument() != null && model.getDocument().getLength() != 0 );
    	menuItems.get(Item.REOPEN).setEnabled(model.getDocument() != null && model.getFile() != null && (!model.isSynchronized()));
    	menuItems.get(Item.SAVE).setEnabled(model.getDocument() != null && model.getFile() != null && (!model.isSynchronized()));
    	menuItems.get(Item.SAVE_AS).setEnabled(model.getDocument()!= null);
    	menuItems.get(Item.CLOSE).setEnabled(model.getDocument()!= null);
    	
    	
        /*****************/
    }
    
    /**
     * Met à jour le Viewport du JScrollPane en fonction de la présence d'un
     *  document dans le modèle.
     * Remplace le document de la zone de texte par celui du modèle quand c'est
     *  nécessaire.
     */
    private void updateScrollerAndEditorComponents() {
        /*****************/
        /** A COMPLETER **/
    	scroll
        /*****************/
    }
    
    /**
     * Met à jour la barre d'état.
     */
    private void updateStatusBar() {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Demande une confirmation de poursuite d'action.
     * @post
     *     result == true <==>
     *         le modèle était synchronisé
     *         || il n'y avait pas de document dans le modèle
     *         || le document était en cours d'édition mais l'utilisateur
     *            a répondu positivement à la demande de confirmation
     */
    private boolean confirmAction() {
		return false;
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Demande une confirmation d'écrasement de fichier.
     * @pre
     *     f != null
     * @post
     *     result == true <==>
     *         le fichier n'existe pas
     *         || le fichier existe mais l'utilisateur a répondu positivement
     *            à la demande de confirmation
     */
    private boolean confirmReplaceContent(File f) {
		return false;
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Toute erreur de l'application due au comportement de l'utilisateur doit
     *  être interceptée et transformée en message présenté dans une boite de
     *  dialogue.
     */
    private void displayError(String m) {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Permet au client de sélectionner un fichier de sauvegarde en choisissant
     *  un nom de fichier à l'aide d'un JFileChooser.
     * Si le nom de fichier choisi n'existe pas encore, un nouveau fichier est
     *  créé avec ce nom.
     * Retourne null si et seulement si :
     * - l'utilisateur a annulé l'opération,
     * - le nom choisi correspond à un élément préexistant du système de fichier
     *   mais cet élément n'est pas un fichier
     * - le nom choisi ne correspond pas à un élément préexistant du système de
     *   fichier mais le fichier n'a pas pu être créé.
     * Dans les deux derniers cas, une boite de dialogue vient de plus décrire
     *  le problème.
     */
    private File selectSaveFile() {
		return null;
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Permet au client de sélectionner un fichier à charger en choisissant
     *  un nom de fichier à l'aide d'un JFileChooser.
     * Retourne null si et seulement si :
     * - l'utilisateur a annulé l'opération,
     * - le nom choisi ne correspond pas un fichier existant.
     * Dans ce dernier cas, une boite de dialogue vient de plus décrire
     *  le problème.
     */
    private File selectLoadFile() {
		return null;
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    /*****************/
}

/**
 * Met à jour la barre d'état.
 */
private void updateStatusBar() {
    /*****************/
    /** A COMPLETER **/
    /*****************/
}

/**
 * Demande une confirmation de poursuite d'action.
 * @post
 *     result == true <==>
 *         le modèle était synchronisé
 *         || il n'y avait pas de document dans le modèle
 *         || le document était en cours d'édition mais l'utilisateur
 *            a répondu positivement à la demande de confirmation
 */
private boolean confirmAction() {
	return false;
    /*****************/
    /** A COMPLETER **/
    /*****************/
}

/**
 * Demande une confirmation d'écrasement de fichier.
 * @pre
 *     f != null
 * @post
 *     result == true <==>
 *         le fichier n'existe pas
 *         || le fichier existe mais l'utilisateur a répondu positivement
 *            à la demande de confirmation
 */
private boolean confirmReplaceContent(File f) {
	return false;
    /*****************/
    /** A COMPLETER **/
    /*****************/
}

/**
 * Toute erreur de l'application due au comportement de l'utilisateur doit
 *  être interceptée et transformée en message présenté dans une boite de
 *  dialogue.
 */
private void displayError(String m) {
    /*****************/
    /** A COMPLETER **/
    /*****************/
}

/**
 * Permet au client de sélectionner un fichier de sauvegarde en choisissant
 *  un nom de fichier à l'aide d'un JFileChooser.
 * Si le nom de fichier choisi n'existe pas encore, un nouveau fichier est
 *  créé avec ce nom.
 * Retourne null si et seulement si :
 * - l'utilisateur a annulé l'opération,
 * - le nom choisi correspond à un élément préexistant du système de fichier
 *   mais cet élément n'est pas un fichier
 * - le nom choisi ne correspond pas à un élément préexistant du système de
 *   fichier mais le fichier n'a pas pu être créé.
 * Dans les deux derniers cas, une boite de dialogue vient de plus décrire
 *  le problème.
 */
private File selectSaveFile() {
	return null;
    /*****************/
    /** A COMPLETER **/
    /*****************/
}

/**
 * Permet au client de sélectionner un fichier à charger en choisissant
 *  un nom de fichier à l'aide d'un JFileChooser.
 * Retourne null si et seulement si :
 * - l'utilisateur a annulé l'opération,
 * - le nom choisi ne correspond pas un fichier existant.
 * Dans ce dernier cas, une boite de dialogue vient de plus décrire
 *  le problème.
 */
private File selectLoadFile() {
	return null;
    /*****************/
    /** A COMPLETER **/
    /*****************/
}
}

}
