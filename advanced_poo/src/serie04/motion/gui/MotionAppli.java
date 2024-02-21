package serie04.motion.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;
import java.util.Map;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import serie04.motion.model.Animator;
import serie04.motion.util.TickEvent;
import serie04.motion.util.TickListener;
import serie04.motion.view.Animable;

public class MotionAppli {
    
    private static final int MAJOR_TICK = 10;

    // ATTRIBUTS

    private final JFrame frame;
    private final Map<BKey, JButton> buttons;
    private final JSlider slider;
    private final Animable animable;
    private final Animator animator;

    // CONSTRUCTEURS

    public MotionAppli(Animable v, Animator m) {
        // MODELE
        animator = m;
        // VUE
        frame = new JFrame();
        slider = buildSlider();
        buttons = buildButtonsMap();
        animable = v;
        placeComponents();
        // CONTROLEUR
        connectControllers();
    }

    // COMMANDES

    public void display() {
        // initialisation de la vue en fonction de l'état du modèle
        updateButtons();
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS

    private JSlider buildSlider() {
        JSlider result = new JSlider(0, 0);
        result.setPaintTicks(true);
        result.setPaintLabels(true);
        result.setSnapToTicks(true);
        configTickSpacingFor(result);
        // il faut fixer les deux valeurs suivantes dans cet ordre pour être sûr
        // que slider.getValue() soit égal à animator.getSpeed()
        result.setMaximum(animator.getMaxSpeed());
        result.setValue(animator.getSpeed());
        return result;
    }
    
    private void configTickSpacingFor(JSlider js) {
        int max = animator.getMaxSpeed();
        int n = MAJOR_TICK;
        while (max / n < 2) {
            n = n / 2;
        }
        js.setMinorTickSpacing(1);
        js.setMajorTickSpacing(n);
    }
    
    private Map<BKey, JButton> buildButtonsMap() {
        Map<BKey, JButton> result = new EnumMap<BKey, JButton>(BKey.class);
        for (BKey k : BKey.values()) {
            JButton b = new JButton(k.label);
            b.setName(k.name());
            result.put(k, b);
        }
        return result;
    }
    
    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            for (BKey k : BKey.values()) {
                p.add(buttons.get(k));
            }
        } //--
        frame.add(p, BorderLayout.NORTH);

        p = new JPanel();
        { //--
            p.add((Component) animable);
        } //--
        frame.add(p, BorderLayout.CENTER);

        frame.add(slider, BorderLayout.SOUTH);
    }

    private void connectControllers() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pilotage de l'animation par les boutons
        /*****************/
        /** A COMPLETER **/
        buttons.get(BKey.START).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animator.start();
			}
		});
        
        buttons.get(BKey.PAUSE).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animator.pause();
				slider.setValue(animator.getSpeed());
			}
		});
        
        buttons.get(BKey.RESUME).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animator.resume();
				slider.setValue(animator.getSpeed());
			}
		});
        
        buttons.get(BKey.TERMINATE).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				animator.stop();
			}
		});
        
        
        
        /*****************/
        
        // Modification de la vitesse de l'animation
        /*****************/
        /** A COMPLETER **/
        slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				animator.setSpeed(slider.getValue());
			}
		});
        /*****************/

        // Observation des ticks
        /*****************/
        /** A COMPLETER **/
        animator.addTickListener(new TickListener() {
			
			@Override
			public void tickOccurred(TickEvent e) {
				animable.animate();
			}
		});
        /*****************/

        // Observation des changements d'état
        /*****************/
        /** A COMPLETER **/
        animator.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				updateButtons();
			}
		});
        /*****************/
    }
    
    private void updateButtons() {
        /*****************/
        /** A COMPLETER **/
    	if(animator.isRunning()) {
            buttons.get(BKey.START).setEnabled(false);
            buttons.get(BKey.PAUSE).setEnabled(true);
            buttons.get(BKey.RESUME).setEnabled(false);
            buttons.get(BKey.TERMINATE).setEnabled(true);
        }
        if(animator.isPaused()) {
            buttons.get(BKey.START).setEnabled(false);
            buttons.get(BKey.PAUSE).setEnabled(false);
            buttons.get(BKey.RESUME).setEnabled(true);
            buttons.get(BKey.TERMINATE).setEnabled(true);
        }
        if(animator.isResumed()) {
            buttons.get(BKey.START).setEnabled(false);
            buttons.get(BKey.PAUSE).setEnabled(true);
            buttons.get(BKey.RESUME).setEnabled(false);
            buttons.get(BKey.TERMINATE).setEnabled(true);
        }
        if(animator.hasStopped()) {
            buttons.get(BKey.START).setEnabled(true);
            buttons.get(BKey.PAUSE).setEnabled(false);
            buttons.get(BKey.RESUME).setEnabled(false);
            buttons.get(BKey.TERMINATE).setEnabled(false);
        }
        /*****************/
    }

    // TYPES IMBRIQUES

    /**
     * Regroupement :
     * - des tests indiquant si les boutons doivent être actifs ou non,
     * - du code de pilotage de l'animation,
     * - de l'étiquette de chaque bouton.
     */
    private enum BKey {
        START("Start") {
            @Override boolean enabledValue(Animator anim) {
                return !anim.hasStarted();
            }
            @Override void executeCommand(Animator anim) {
                anim.start();
            }
        },
        PAUSE("Pause") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isResumed();
            }
            @Override void executeCommand(Animator anim) {
                anim.pause();
            }
        },
        RESUME("Resume") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isPaused();
            }
            @Override void executeCommand(Animator anim) {
                anim.resume();
            }
        },
        TERMINATE("Terminate") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isRunning();
            }
            @Override void executeCommand(Animator anim) {
                anim.stop();
            }
        };

        private String label;

        BKey(String lbl) {
            label = lbl;
        }

        abstract boolean enabledValue(Animator anim);
        abstract void executeCommand(Animator anim);
    }
    
}
