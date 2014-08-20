package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreSpacePropVars extends OneOrMore<SpacePropVar> {

    public OneOrMoreSpacePropVars() {
        super(new SpacePropVar(true));
        addSubnode(new OneOrMoreSpacePropVars(false));
        addSubnode(new SpacePropVar());
        addSubnode(new OneOrMoreSpacePropVars(false));
    }

    public OneOrMoreSpacePropVars(boolean required) {
        super(required, new SpacePropVar());
    }

    public void addAtNonPlaceholderPosition(int i, SpacePropVar spv) {
        if (i < 0 || i > getNonPlaceholders().size()) {
            throw new RuntimeException();
        }

        if (getSubnodes().isEmpty()) {
            if (i > 0) {
                throw new RuntimeException();
            }
            addSubnode(new OneOrMoreSpacePropVars(false));
            addSubnode(spv);
            addSubnode(new OneOrMoreSpacePropVars(false));
        } else if (getSubnodes().size() >= 3) {
            if (i > getNonPlaceholders().size()) {
                throw new RuntimeException();
            }
            if (i == 0) {
                addSubnode(0, spv);
                addSubnode(0, new OneOrMoreSpacePropVars(false));
            } else if (i == getNonPlaceholders().size()) {
                addSubnode(spv);
                addSubnode(new OneOrMoreSpacePropVars(false));
            } else {
                SpacePropVar ithNonPlaceholder = getNonPlaceholders().get(i);
                int j = getSubnodes().indexOf(ithNonPlaceholder);
                addSubnode(j, spv);
                addSubnode(j+1,new OneOrMoreSpacePropVars());
            }
        }
        
        getProofModel().getProofView().update();
    }

    private List<SpacePropVar> getNonPlaceholders() {
        List<SpacePropVar> nonPlaceholders = new ArrayList<SpacePropVar>();

        for (Node n : getSubnodes()) {
            if (n instanceof SelectableNode) {
                SelectableNode selectableSubnode = (SelectableNode) n;
                if (!selectableSubnode.isPlaceholder()) {
                    nonPlaceholders.add((SpacePropVar) n);
                }
            }
        }

        return nonPlaceholders;
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
