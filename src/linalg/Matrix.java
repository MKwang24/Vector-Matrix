package linalg;

/*** A class that represents a two dimensional real-valued (double) matrix
 *   and supports various matrix computations required in linear algebra.
 *
 */
public class Matrix {

	private int _nRows; // Number of rows in this matrix; nomenclature: _ for data member, n for integer
	private int _nCols; // Number of columns in this matrix; nomenclature: _ for data member, n for integer
	// TODO: add your own data member to represent the matrix content
	//       you could use a 2D array, or an array of Vectors (e.g., for each row)
	private double [][] _adVal ; 
	/** Allocates a new matrix of the given row and column dimensions
	 * 
	 * @param rows
	 * @param cols
	 * @throws LinAlgException if either rows or cols is <= 0
	 */
	public Matrix(int rows, int cols) throws LinAlgException {
		// TODO: hint: see the corresponding Vector constructor

		if (rows<=0 || cols <= 0 )
			throw new LinAlgException("Both dimensions ("+cols + ","+rows+") must be greater than 0");
		_nCols = cols;
		_nRows = rows;
		_adVal = new double[_nRows][_nCols];
		
	}
	
	/** Copy constructor: makes a new copy of an existing Matrix m
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param m
	 */
	public Matrix(Matrix m) {
		// TODO: hint: see the corresponding Vector "copy constructor" for an example
		_nCols = m._nCols;
		_nRows = m._nRows;
		
		_adVal = new double[_nRows][_nCols]; // This allocates an array of size _nDim
		for (int index = 0; index < _nRows; index++)
			for (int j = 0; j < _nCols; j++)
			_adVal[index][j] = m._adVal[index][j];
	}

	/** Constructs a String representation of this Matrix
	 * 
	 */
	public String toString() {
		// TODO: hint: see Vector.toString() for an example
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <_nRows; i++) {
		sb.append("[");
		for (int j = 0; j < _nCols; j++)
			sb.append(String.format(" %6.3f ", _adVal[i][j])); // Append each vector value in order
		sb.append(" ]\n");
		}
		return sb.toString();
	}

	/** Tests whether another Object o (most often a matrix) is a equal to *this*
	 *  (i.e., are the dimensions the same and all elements equal each other?)
	 * 
	 * @param o the object to compare to
	 */
	public boolean equals(Object o) {
		// TODO: hint: see Vector.equals(), you can also use Vector.equals() for checking equality 
		//             of row vectors if you store your matrix as an array of Vectors for rows
		if (o instanceof Matrix) {
			Matrix m = (Matrix)o;
			if (_nCols!= m._nCols || _nRows != m._nRows)
				return false;
			for (int index = 0; index < _nRows ; index++) 
				for (int j = 0 ; j <_nCols; j++)
					if (_adVal[index][j]!= m._adVal[index][j])
						return false;
			return true;
		}else
			return false;
		}
		// TODO: this should not always return false!
		 // This should not always return false!
	
	
	/** Return the number of rows in this matrix
	 *   
	 * @return 
	 */
	public int getNumRows() {
		// TODO (this should not return -1!)
		return this._nRows;
	}

	/** Return the number of columns in this matrix
	 *   
	 * @return 
	 */
	public int getNumCols() {
		// TODO (this should not return -1!)
		return this._nCols;
	}

	/** Return the scalar value at the given row and column of the matrix
	 * 
	 * @param row
	 * @param col
	 * @return
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public double get(int row, int col) throws LinAlgException {
		// TODO (this should not return -1!)
		if (row> (_nRows-1) || col > (_nCols-1) ) {
			throw new LinAlgException("One or both indices ("+ row + ","+ col+") are out of bounds ([0, "+_nRows+"],[0, "+ _nCols+"])");
		}
		if (row<0||col<0) {
			throw new LinAlgException("One or both indices ("+ row + ","+ col+") are out of bounds ([0, "+_nRows+"],[0, "+ _nCols+"])");
		}
		double val1 = _adVal[row][col]; // extract the value of index (row, col)
		return val1;
	}
	
	/** Return the Vector of numbers corresponding to the provided row index
	 * 
	 * @param row
	 * @return
	 * @throws LinAlgException if row is out of bounds
	 */
	public Vector getRow(int row) throws LinAlgException {
		// TODO (this should not return null!)
		if (row < 0 || row> (_nRows-1) ) {
			throw new LinAlgException("Row index ("+ row + ") out of bounds [0, "+_nRows+"])");
		}
		Vector row1 = new Vector(_nCols);
		for (int i=0; i<_nCols; i++) {
			row1.set(i, this._adVal[row][i]); // extract each element in the picked row (Row [row])
			// set the corresponding index of extracted value 
		}
		return row1;
	}

	/** Set the row and col of this matrix to the provided val
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public void set(int row, int col, double val) throws LinAlgException {
		// TODO
		if (row > (_nRows-1) || col > (_nCols-1)|| row< 0|| col<0) {
			throw new LinAlgException("One or both indices ("+ row + ","+ col+") are out of bounds ([0, "+_nRows+"],[0, "+ _nCols + "])");
		}
		_adVal [row][col] = val; // set the the value of the index (row ,col) of matrix to certain value 
		
	}
	
	/** Return a new Matrix that is the transpose of *this*, i.e., if "transpose"
	 *  is the transpose of Matrix m then for all row, col: transpose[row,col] = m[col,row]
	 *  (should not modify *this*)
	 * 
	 * @return
	 * @throws LinAlgException
	 */
	public Matrix transpose() throws LinAlgException {
		/* Basic Mechanisms 
		 [1 1 1]						[1 3 1]
		 [3 1 1]             ------->   [1 1 1]  
		 [1 1 2]						[1 1 2]
		 */ 
		Matrix transpose = new Matrix(_nCols, _nRows);
		for (int row = 0; row < _nRows; row++) {
			for (int col = 0; col < _nCols; col++) {
				transpose.set(col, row, get(row,col));
			}
		}
		return transpose;
	}

	/** Return a new Matrix that is the square identity matrix (1's on diagonal, 0's elsewhere) 
	 *  with the number of rows, cols given by dim.  E.g., if dim = 3 then the returned matrix
	 *  would be the following:
	 *  
	 *  [ 1 0 0 ]
	 *  [ 0 1 0 ]
	 *  [ 0 0 1 ]
	 * 
	 * @param dim
	 * @return
	 * @throws LinAlgException if the dim is <= 0
	 */
	public static Matrix GetIdentity(int dim) throws LinAlgException {
		// TODO: this should not return null!
		if (dim <=0) {
			throw new LinAlgException ("Size "+dim+" must be greater than 0");
		}
		Matrix iden = new Matrix(dim,dim);
		for (int i = 0; i<dim; i++) {
			iden._adVal[i][i]=1.0;		// Since Identity matrix only has the value of "1.0" in indicies (x,x)	  
			/*[ 1 0 0 ]
			  [ 0 1 0 ]
			  [ 0 0 1 ]
		*/
		}
		return iden;
	}
	
	/** Returns the Matrix result of multiplying Matrix m1 and m2
	 *  (look up the definition of matrix multiply if you don't remember it)
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 * @throws LinAlgException if m1 columns do not match the size of m2 rows
	 */
	public static Matrix Multiply(Matrix m1, Matrix m2) throws LinAlgException{
		// TODO: this should not return null!
		if (m1._nCols != m2._nRows) {
			throw new LinAlgException ("Cannot multiply matrix m1 having "+m1._nCols+ " columns with matrix m2 having "+m2._nRows+" rows");
		}
		Matrix result = new Matrix (m1._nRows,m2._nCols);// initialize a new matrix with given conditions
		for (int i = 0 ; i <m1._nRows; i++) {	// set up the row loop
			
			for (int j = 0; j< m2._nCols; j++) { // set up the column (index of row) loop
				
				result._adVal[i][j] = Vector.InnerProd(m1.getRow(i), m2.transpose().getRow(j)); 
				// transpose the second matrix and get its column (row in the transposed case)
				/*
				 [1 1 1]						[1 3 1]
				 [3 1 1]             ------->   [1 1 1]  
				 [1 1 2]						[1 1 2]
				 */ 
				// then simply use Vector.Innerprod method in which is defined before in Vector, since it is just a simple dot product calculation.  
			}
			
		}
			
		return result;
	}
		
	/** Returns the Vector result of multiplying Matrix m by Vector v (assuming v is a column vector)
	 * 
	 * @param m
	 * @param v
	 * @return
	 * @throws LinAlgException if m columns do match the size of v
	 */
	public static Vector Multiply(Matrix m, Vector v) throws LinAlgException {
		// TODO: this should not return null!
		if (m._nCols != v.getDim()) {
			throw new LinAlgException("Cannot multiply matrix with "+m._nCols+" columns with a vector of dimension "+v.getDim());
		}
		Vector back= new Vector(m._nRows);
		
		for (int i = 0 ; i < m._nRows; i++) {
			double num;
				num = Vector.InnerProd(m.getRow(i),v);// each row times the given vector v, getting the dot product of two vectors. 
			back.set(i, num); // set the result number to corresponding indice
		}
		return back;
	}
}