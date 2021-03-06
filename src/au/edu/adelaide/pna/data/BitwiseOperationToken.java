
package au.edu.adelaide.pna.data;

import au.edu.adelaide.pna.data.util.IllegalActionException;

///////////////////////////////////////////////////////////////////
//// BitwiseOperationToken

/**
 The operations that can be performed on tokens that have bitwise operations.

 @author Steve Neuendorffer
 @version $Id: BitwiseOperationToken.java 57040 2010-01-27 20:52:32Z cxh $
 @since Ptolemy II 2.1
 @Pt.ProposedRating Green (neuendor)
 @Pt.AcceptedRating Yellow (wbwu)
 */
public interface BitwiseOperationToken {
    ///////////////////////////////////////////////////////////////////
    ////                         public methods                    ////

    /** Returns a token representing the bitwise AND of this token and
     *  the given token.
     *  @param rightArgument  The token that is bitwise ANDed with this token.
     *  @return The bitwise AND.
     *  @exception IllegalActionException If the given token is not
     *  compatible for this operation, or the operation does not make
     *  sense for this type.
     */
    public BitwiseOperationToken bitwiseAnd(Token rightArgument)
            throws IllegalActionException;

    /** Returns a token representing the bitwise NOT of this token.
     *  @return The bitwise NOT of this token.
     *  @exception IllegalActionException If the given token is not
     *  compatible for this operation, or the operation does not make
     *  sense for this type.
     */
    public BitwiseOperationToken bitwiseNot() throws IllegalActionException;

    /** Returns a token representing the bitwise OR of this token and
     *  the given token.
     *  @param rightArgument  The token that is bitwise OR'd with this token.
     *  @return The bitwise OR.
     *  @exception IllegalActionException If the given token is not
     *  compatible for this operation, or the operation does not make
     *  sense for this type.
     */
    public BitwiseOperationToken bitwiseOr(Token rightArgument)
            throws IllegalActionException;

    /** Returns a token representing the bitwise XOR of this token and
     *  the given token.
     *  @param rightArgument  The token that is bitwise XOR'd with this token.
     *  @return The bitwise XOR.
     *  @exception IllegalActionException If the given token is not
     *  compatible for this operation, or the operation does not make
     *  sense for this type.
     */
    public BitwiseOperationToken bitwiseXor(Token rightArgument)
            throws IllegalActionException;
}
