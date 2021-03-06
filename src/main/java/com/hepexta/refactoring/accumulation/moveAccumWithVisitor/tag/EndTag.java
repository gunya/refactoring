package com.hepexta.refactoring.accumulation.moveAccumWithVisitor.tag;

import com.hepexta.refactoring.accumulation.moveAccumWithVisitor.Node;
import com.hepexta.refactoring.accumulation.moveAccumWithVisitor.visitor.NodeVisitor;

public class EndTag implements Node {
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visitEndTag(this);
    }
}
