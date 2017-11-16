package cs5500.project.engine.ast;

import com.google.common.hash.HashCode;

public class ASTHashObject {

    /**
     * @param name identifier name of node
     * @param type type of node
     * @param offset offset of node
     * @param length length of node
     */
    public ASTHashObject(String name, Integer type, Integer offset, Integer length) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.length = length;
        this.hash = 0;
    }

    /**
     * Default Constructor
     */
    public ASTHashObject() {}

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return
     */
    public Integer getoffset() {
        return offset;
    }

    /**
     * @param offset
     */
    public void setoffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    private String name;
    private Integer type;
    private Integer offset;
    private Integer length;

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public void addToHash(HashCode hash) {
        this.hash += hash.asInt();
    }

    private Integer hash;
}
