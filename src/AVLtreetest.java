import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/* @author: 110526005
 * 
 * Edited on MacOS 
 * Encoded by UTF-8
 * 
 * 以下測試
 * * 前半段依照各個 public method 進行測試
 * * 後半段（大概800行之後）為
 * 		Boundary tests
 * 		Negative tests
 * 		Performance tests
 * 		Partial Test Oracle
 * 
 * * AvlTree 中的 isBalanced 為自己加入方便檢查子樹高度是否有平衡用的函式
 * 
 * * Code Coverage的部分詢問過助教後，已經寫在報告書之中，再請翻閱檔案中的 pdf 檔
 * 
 * */
class AVLtreetest {
	/*
	 * AVLtree()
	 */

	@Test
	void constructAVLNode_Null_NotNull_byConverage() {
		
		// given
		// 新建一個null的 avlNode，root沒有任何值
		AvlNode avlNode = new AvlNode();
		
		// when
		// 執行完新建以後，應該要進行結果判斷，因為下方assert使用NotNull做判斷，此處when不需要使用
		
		// then 
		// 驗證avlNode的constructor是否成功生成
		// 應該要得到NotNull的驗證
		
		assertNotNull(avlNode);
	}

	@Test
	void constructAVLNode_Root_NotNull_byConverage() {
		
		// given
		// 新建一個有root值的 avlNode
		AvlNode avlNode = new AvlNode(1);
		
		// when
		// 執行完新建以後，應該要進行結果判斷，因為下方assert使用NotNull做判斷，此處when不需要使用
		
		// then
		// 驗證 avlNode的constructor是否成功生成
		// 應該要是得到 Not Null 的結果代表說建構子有成功生成
		assertNotNull(avlNode);
	}
	
	/*
	 * isEmpty()
	 */
	@Test
	void isEmpty_NULL_True_byCoverage() {
		
		// given 
		// 新建一個null的 avlNode，root沒有任何值
		AvlTree avlTree = new AvlTree();
		
		// when 
		// 要針對isEmpty method進行檢查
		
		boolean isEmptyResult = avlTree.isEmpty();
		// then
		// 預期要得到是空的答案
		boolean expectedResult = true;
		
		assertEquals(expectedResult, isEmptyResult);
	}
	
	@Test
	void isEmpty_AVLTreeWithValue_False_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// 於新的AVLTree中新增root=1的節點
		avlTree.insert(1);
		
		// when 
		//avlTree is not null
		// 判斷說是否有新增成功
		boolean result =avlTree.isEmpty();
		
		// then
		// 理應上有新增成功的話就會是有東西，所以isEmpty會回傳 false
		boolean expected = false;
		
		assertEquals(expected, result );
	}
	
	/*
	 * makeEmpty()
	 */
	/* O(log n)*/
	@Test
	void makeEmpty_AVLTreeWithValue_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// 於新的AVLTree中新增root=1的節點
		avlTree.insert(1);
		
		// when 
		// 測試我們的 makeEmpty method 是否有成功執行
		// 當insert一個Node進去後，透過makeEmpty method應該要變成空的，所以再使用 isEmpty進行檢查
		
		avlTree.makeEmpty();
		boolean result =avlTree.isEmpty();
		// then
		// 應該要能順利刪除且樹為空，回傳true
		boolean expected = true;
		
		assertEquals(expected, result);
	}
	
	@Test
	void makeEmpty_AVLTreeWithValue_False_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// 於新的AVLTree中新增root=1的節點
		avlTree.insert(1);
		
		// when 
		// 驗證 makeEmpty 的功效
		// 這個測試不使用 makeEmpty() method
		// 仍然使用isEmpty進行驗證
		
		boolean result =avlTree.isEmpty();
		
		// then
		// 因為沒有使用 makeEmpty() method，理應當為 false
		boolean expected = false;
		
		assertEquals(expected, result);
	}
	
	


	/*
	 * 以下開始AvlTree 中的 isBalanced 為自己加入方便檢查用的函式
	 */
	
	/*
	 * insert()
	 */
	/* O(log n)*/
	
	@Test
	void insertCheckBalance_0node_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// No any nodes be given into AVLTree
		// insert 0 nodes
		
		// when 
		// 因為沒有任何Node在其中，但我們還是檢查看看是否有符合AVL Tree的規格
		
		boolean resultEmpty = avlTree.isEmpty();
		boolean resultBalanced = avlTree.isBalanced();
		
		// then
		// 就算沒有insert任何東西，樹應該還是要維持平衡
		// AVL Tree的 使用isEmpty檢查後也要為空
		
		boolean expectedHeight = true;
		
		assertTrue(resultEmpty);
		assertEquals(expectedHeight, resultBalanced);
	}
	@Test
	void insertCheckBalance_1node_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 測試 insert method 輸入一個元素的情形
		// 這裡在新的AVL Tree中插入元素1 
		
		avlTree.insert(1);
		
		// when 
		// 如果insert有正常執行，insert以後應該還是要維持AVL樹高平衡的條件
		// 而這邊也藉重 inorder來進行輸出輔助
		boolean resultBalanced = avlTree.isBalanced();		
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期樹高平衡要是符合預期，所以檢查完回傳true
		// 預期 inorder 的輸出會是 1 
		boolean expectedHeight = true;
		String expectInorder = "1";
		
		assertEquals(expectedHeight,resultBalanced);
		assertEquals(expectInorder,resultInorder);
	}
	
	@Test
	void insertCheckBalance_2nodes_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given
		// 測試 insert method 輸入兩個元素的情形
		// 這裡在新的AVL Tree中插入元素1、2
		avlTree.insert(1);
		avlTree.insert(2);
		
		// when 
		// 如果insert有正常執行，insert以後應該還是要維持AVL樹高平衡的條件
		// 而這邊也藉重 inorder來進行輸出輔助
		boolean resultBalanced = avlTree.isBalanced();	
		
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期樹高平衡要是符合預期，所以檢查完回傳true
		// 預期 inorder 的輸出會是 1 2
		
		boolean expectedHeight = true;
		String expectInorder = "1 2";
		
		assertEquals(expectedHeight, resultBalanced);
		
		assertEquals(expectInorder, resultInorder);
	}
	@Test
	void insertCheckBalance_3nodes_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 測試 insert method 輸入三個元素的情形
		// 這裡在新的AVL Tree中插入元素1、2、3
		
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 如果insert有正常執行，insert以後應該還是要維持AVL樹高平衡的條件
		// 而這邊也藉重 inorder來進行輸出輔助
		boolean resultBalanced = avlTree.isBalanced();	
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期樹高平衡要是符合預期，所以檢查完回傳true
		// 預期 inorder 的輸出會是 1 2 3
		boolean expectedHeight = true;
		String expectInorder = "1 2 3";
		
		assertEquals(expectedHeight, resultBalanced);
		
		assertEquals(expectInorder, resultInorder);
	}
	@Test
	void insertCheckBalance_4nodes_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 測試 insert method 輸入四個元素的情形
		// 這裡在新的AVL Tree中插入元素1、2、3、4
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		avlTree.insert(4);
		
		// when 
		// 如果insert有正常執行，insert以後應該還是要維持AVL樹高平衡的條件
		// 而這邊也藉重 inorder來進行輸出輔助
		boolean resultBalanced = avlTree.isBalanced();	
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期樹高平衡要是符合預期，所以檢查完回傳true
		// 預期 inorder 的輸出會是 1 2 3 4
		boolean expectedHeight = true;
		String expectInorder = "1 2 3 4";
		
		assertEquals(expectedHeight, resultBalanced);
		assertEquals(expectInorder, resultInorder);
	}
	
	/*
	 *  Insert 中的 rotate 進行測試
	 */
	@Test
	void insertCheckRotateLL_321_True_byPartition() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 因為要測試LL的時候 insert是否有成功旋轉
		// 所以這邊刻意輸入由大到小的測資，來達到BST為左子樹接左子樹的狀態
		
		avlTree.insert(3);
		avlTree.insert(2);
		avlTree.insert(1);
		
		/* LL
		 *       3        2
		 *     2    ->  1   3
		 *   1 
		 *   
		 * */
		
		// when 
		// insert 完要確定是否有符合子樹間樹高差距都要在-1 0 1之間，需使用isBalanced進行檢查
		// 也要檢查看看inorder的輸出是否是由小排到大
		// 當上述兩個條件都有達成就是有進行rotate的處理
		
		boolean resultBalanced = avlTree.isBalanced();		
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期有符合樹高規定的標準
		// 也預期輸出會為1,2,3
		boolean expectedHeight = true;
		String expectInorder = "1 2 3";
		
		assertEquals(expectedHeight, resultBalanced);
		assertEquals(expectInorder, resultInorder);
	}
	
	@Test
	void insertCheckRotateRR_123_True_byPartition() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 因為要測試RR的時候 insert是否有成功旋轉
		// 所以這邊刻意輸入由小到大的測資，來達到BST為右子樹接右子樹的狀態
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		
		/* RR                   
		 *       1             2
		 *         2    ->   1   3
		 *          3
		 *   
		 * */
		
		
		
		// when 
		// insert 完要確定是否有符合子樹間樹高差距都要在-1 0 1之間，需使用isBalanced進行檢查
		// 也要檢查看看inorder的輸出是否是由小排到大
		// 當上述兩個條件都有達成就是有進行rotate的處理
		boolean resultBalanced = avlTree.isBalanced();	
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期有符合樹高規定的標準
		// 也預期輸出會為1,2,3
		boolean expectedHeight = true;
		String expectInorder = "1 2 3";
				
		assertEquals(expectedHeight, resultBalanced);
		assertEquals(expectInorder, resultInorder);
		
	}
	
	@Test
	void insertCheckRotateLR_312_True_byPartition() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 因為要測試LR的時候 insert是否有成功旋轉
		// 所以這邊刻意輸入3,1,2測資，來達到BST為左子樹接右子樹的狀態
		avlTree.insert(3);
		avlTree.insert(1);
		avlTree.insert(2);

		/* LR
		 *        3         2
		 *    1      ->  1     3
		 *      2
		 * 
		 * */
		
		// when 
		// insert 完要確定是否有符合子樹間樹高差距都要在-1 0 1之間，需使用isBalanced進行檢查
		// 也要檢查看看inorder的輸出是否是由小排到大
		// 當上述兩個條件都有達成就是有進行rotate的處理
		boolean resultBalanced = avlTree.isBalanced();	
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期有符合樹高規定的標準
		// 也預期輸出會為1,2,3
		boolean expectedHeight = true;
		String expectInorder = "1 2 3";
				
		assertEquals(expectedHeight, resultBalanced);
		assertEquals(expectInorder, resultInorder);
	
		
	}
	@Test
	void insertCheckRotateRL_132_True_byPartition() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 因為要測試RL的時候 insert是否有成功旋轉
		// 所以這邊刻意輸入1,3,2測資，來達到BST為右子樹接左子樹的狀態
		avlTree.insert(1);
		avlTree.insert(3);
		avlTree.insert(2);
		
		/* RL
		 *       1             2
		 *           3  ->  1     3
		 *         2  
		 * 
		 * */
		
		
		// when 
		// insert 完要確定是否有符合子樹間樹高差距都要在-1 0 1之間，需使用isBalanced進行檢查
		// 也要檢查看看inorder的輸出是否是由小排到大
		// 當上述兩個條件都有達成就是有進行rotate的處理
		boolean resultBalanced = avlTree.isBalanced();	
		String resultInorder = avlTree.inorder();
		
		// then
		// 預期有符合樹高規定的標準
		// 也預期輸出會為1,2,3
		boolean expectedHeight = true;
		String expectInorder = "1 2 3";
		
				
		assertEquals(expectedHeight, resultBalanced);
		assertEquals(expectInorder, resultInorder);
		
	}
	
	@Test
	void insertCheckDuplicate_111_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 要檢查看看如果輸入一樣的值時，是否會成功走入程式碼中duplicate的else之中
		// 測試輸入三組一樣的元素1
		avlTree.insert(1);
		avlTree.insert(1);
		avlTree.insert(1);
		
		// when 
		// insert 完要確定是否有符合子樹間樹高差距都要在-1 0 1之間，需使用isBalanced進行檢查
		// 接著使用 countNodes method進行個數檢查，確認看看是否有排除duplicate的情況
		
		boolean resultBalanced = avlTree.isBalanced();					
		int nodeCount = avlTree.countNodes();
		
		// then
		// 預期仍舊符合樹高，回傳true
		// 也預期出現一樣的數時，應該最後只有insert一樣的元素中的第一個，所以這個測試雖然輸入3個1，但是最終應該要是1個1
		boolean expectedHeight = true;
		int expectNodeCount = 1;
		
		assertEquals(expectedHeight, resultBalanced);
		
		assertEquals(expectNodeCount, nodeCount);
		
	}
	
	/*
	 * countNodes
	 */
	@Test
	void countNodes_123_3_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// insert 3個不同的元素建一個 AVL Tree
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 測試剛剛輸入完後，目前的計算Node的method 要能夠將Node總數計算正確
		int nodeCount = avlTree.countNodes();				
		
		// then
		// 我們已知 insert三個不同的數，所以數出來應該也要是3
		int expectNodeCount = 3;
		
		assertEquals(expectNodeCount, nodeCount);
	}
	@Test
	void countNodes_EmptyAVLTree_0_byCoverage() {
		
		// given 
		// 為了測試空的AVL Tree是否也能順利計算 Node 數量，這裡不進行任何insert，只有生成AvlTree的物件
		AvlTree avlTree = new AvlTree();
		
		// when 
		// 測試剛剛建立後後，目前的計算Node的method 是否能將空AVL Tree Node總數計算正確
		int nodeCount = avlTree.countNodes();				
		
		// then
		// 我們已知沒有進行任何insert，所以數出來應該要是0個
		int expectNodeCount = 0;
		
		assertEquals(expectNodeCount, nodeCount);
	}
	
	/*
	 * search()
	 */
	/* O(log n)*/
	@Test
	void search_EmptyAVLTree_False_byCoverage() {
		
		// given 
		// 為了測試空的AVL Tree是否也能順利進行search，這裡不進行任何insert，只有生成AvlTree的物件
		AvlTree avlTree = new AvlTree();
		
		// when
		// 要在這個空的AVL Tree之中搜尋0這個元素
		boolean isFound = avlTree.search(0);
		
		// then
		// 雖然為空AVL Tree，但是還是要能夠進行search與回傳是否有找到
		// 這個回傳的結果應該要是找不到 false
		boolean expectFound = false;
		
		assertEquals(expectFound, isFound);
	}
	
	@Test
	void search_AVLTree_True_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 測試有元素的AVL Tree樹是否能夠進行搜尋，這邊先建立具有1,2,3元素的 AVL Tree
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 進行search功能測試，目標尋找元素1
		boolean isFound = avlTree.search(1);			
		
		// then
		// 因為我們在設計測試時已經將1放入AVL Tree之中，所以是要能夠找到的，所以要回傳true
		boolean expectFound = true;
		
		assertEquals(expectFound, isFound);
	}
	
	@Test
	void search_AVLTree_False_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given 
		// 測試有元素的AVL Tree樹是否能夠進行搜尋，這邊先建立具有1,2,3元素的 AVL Tree
		avlTree.insert(1);
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 進行search功能測試，目標尋找元素4
		boolean isFound = avlTree.search(4);	
		
		// then
		// 因為我們在設計測試時並沒有將4放入AVL Tree之中，所以是不能夠找到，所以要回傳false
		boolean expectFound = false;
		
		assertEquals(expectFound, isFound);
	}
	
	/*
	 * inorder()
	 */
	@Test
	void inorder_0nodeAVLTree_Null_byPartition() {
		// 測試 空 AVL Tree 是否也能順利執行inorder
		
		// given 
		// 只建立一個空的AVL Tree，不進行任何insert
		AvlTree avlTree = new AvlTree();
		
		// when 
		// 使用inorder時是否能成功運作
		String inorderResult = avlTree.inorder() ;				
		
		// then
		// 輸出上因為avlTree中沒有元素，所以這邊應該是要輸出空字串
		String expectInorder ="";
		
		assertEquals(expectInorder, inorderResult);
	}
	
	@Test
	void inorder_1nodeAVLTree_1_byPartition() {
		// 測試 有一個node的AVL Tree 是否也能順利執行inorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert一個元素以進行驗證
		avlTree.insert(1);
		
		// when 
		// 使用inorder時是否能成功運作
		String inorderResult = avlTree.inorder() ;	
		
		// then
		// 輸出上因為avlTree中只有元素1，所以這邊應該是要輸出1
		String expectInorder ="1";
		
		assertEquals(expectInorder, inorderResult);
	}
	

	@Test
	void inorder_2nodesRootLeftAVLTree_12_byPartition() {
		// 測試 有兩個node且為左子樹關係的AVL Tree 是否也能順利執行inorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(1);
		
		// when 
		// 使用inorder時是否能成功運作
		String inorderResult = avlTree.inorder() ;		
		
		// then
		// 輸出上因為avlTree中有元素1,2，inorder的找法這邊應該是要輸出1 2
		String expectInorder ="1 2";
		
		assertEquals(expectInorder, inorderResult);
	}
	
	@Test
	void inorder_2nodesRootRightAVLTree_23_byPartition() {
		// 測試 有兩個node且為右子樹關係的AVL Tree 是否也能順利執行inorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 使用inorder時是否能成功運作
		String inorderResult = avlTree.inorder() ;		
		
		// then
		// 輸出上因為avlTree中有元素2,3，inorder的找法這邊應該是要輸出2,3
		String expectInorder ="2 3";
		
		assertEquals(expectInorder, inorderResult);
	}
	
	@Test
	void inorder_3nodesAVLTree_123_byPartition() {
		// 測試 有三個node關係的AVL Tree 是否也能順利執行inorder
		
		AvlTree avlTree = new AvlTree();
		// given 
		// insert三個元素，稍早有驗證過rotate在insert時正常執行，所以這裡可以不用考慮rotate，進行驗證
		avlTree.insert(1);
		avlTree.insert(3);
		avlTree.insert(2);
		
		// when 
		// 使用inorder時是否能成功運作
		String inorderResult = avlTree.inorder() ;						
		
		// then
		// 輸出上因為avlTree中有元素1,3,2，inorder的找法這邊應該是要輸出1,2,3
		String expectInorder ="1 2 3";
		
		assertEquals(expectInorder, inorderResult);
	}
	
	/*
	 * preorder()
	 */
	@Test
	void preorder_0nodeAVLTree_Null_byPartition() {
		// 測試 空 AVL Tree 是否也能順利執行preorder
		
		
		// given 
		// 只建立一個空的AVL Tree，不進行任何insert
		AvlTree avlTree = new AvlTree();
		
		// when 
		// 使用preoder時是否能成功運作
		String preorderResult = avlTree.preorder() ;	
		
		// then
		// 輸出上因為AVL Tree中沒有元素，所以這邊應該是要輸出空字串
		String expectPreorder ="";
		
		assertEquals(expectPreorder, preorderResult);
	}
	
	@Test
	void preorder_1nodeAVLTree_1_byPartition() {
		// 測試 有一個node的AVL Tree 是否也能順利執行preorder
		
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert一個元素以進行驗證
		avlTree.insert(1);
		
		// when 
		// 使用preorder時是否能成功運作
		String preorderResult = avlTree.preorder() ;		
		
		// then
		// 輸出上因為AVL Tree中元素為1，所以這邊應該是要輸出1	
		String expectPreorder ="1";
		
		assertEquals(expectPreorder, preorderResult);
	}
	

	@Test
	void preorder_2nodesRootLeftAVLTree_21_byPartition() {
		// 測試 有兩個node且為左子樹關係的AVL Tree 是否也能順利執行preorder
		AvlTree avlTree = new AvlTree();
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(1);
		
		// when 
		// 使用preorder時是否能成功運作
		String preorderResult = avlTree.preorder() ;
		
		// then
		// 輸出上因為AVL Tree中元素為2,1，所以這邊應該是要輸出2,1	
		String expectPreorder ="2 1";
		
		
		assertEquals(expectPreorder, preorderResult);
	}
	
	@Test
	void preorder_2nodesRootRightAVLTree_23_byPartition() {
		// 測試 有兩個node且為右子樹關係的AVL Tree 是否也能順利執行preorder
		AvlTree avlTree = new AvlTree();
	
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 使用preorder時是否能成功運作
		String preorderResult = avlTree.preorder() ;

		// then
		// 輸出上因為AVL Tree中元素為2,3，所以這邊應該是要輸出2,3	
		String expectPreorder ="2 3";
		
		assertEquals(expectPreorder, preorderResult);
	}
	
	@Test
	void preorder_3nodesAVLTree_213_byPartition() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert三個元素，稍早有驗證過rotate在insert時正常執行，所以這裡可以不用考慮rotate，進行驗證
		avlTree.insert(1);
		avlTree.insert(3);
		avlTree.insert(2);
		
		// when 
		// 使用preorder時是否能成功運作
		String preorderResult = avlTree.preorder() ;					
		
		// then
		// 輸出上因為AVL Tree中元素為1,3,2，所以這邊應該是要輸出2,1,3	
		String expectPreorder ="2 1 3";
		
		assertEquals(expectPreorder, preorderResult);
	}
	
	/*
	 * postorder()
	 */
	
	@Test
	void postorder_0nodeAVLTree_Null_byPartition() {
		// 測試 空 AVL Tree 是否也能順利執行postorder
		AvlTree avlTree = new AvlTree();
	
		// given 
		// 只建立一個空的AVL Tree，不進行任何insert
		
		// when 
		// 使用postorder時是否能成功運作
		String postorderResult = avlTree.postorder() ;		
		
		// then
		// 輸出上因為AVL Tree中元素為空，所以這邊應該是要輸出空字串	
		String expectPostorder ="";
		
		assertEquals(expectPostorder, postorderResult);
	}
	
	@Test
	void postorder_1nodeAVLTree_1_byPartition() {
		// 測試 有一個node的AVL Tree 是否也能順利執行postorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert一個元素以進行驗證
		avlTree.insert(1);
		
		// when 
		// 使用postorder時是否能成功運作
		String postorderResult = avlTree.postorder() ;								
		
		// then
		// 輸出上因為AVL Tree中元素為1，所以這邊應該是要輸出1	
		String expectPostorder ="1";
		
		assertEquals(expectPostorder, postorderResult);
	}
	

	@Test
	void postorder_2nodesRootLeftAVLTree_12_byPartition() {
		// 測試 有兩個node且為左子樹關係的AVL Tree 是否也能順利執行postorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(1);
		
		// when 
		// 使用postorder時是否能成功運作
		String postorderResult = avlTree.postorder() ;						
		
		// then
		// 輸出上因為AVL Tree中元素為2,1，所以這邊應該是要輸出1,2	
		String expectPostorder ="1 2";
		
		assertEquals(expectPostorder, postorderResult);
	}
	
	@Test
	void postorder_2nodesRootRightAVLTree_32_byPartition() {
		// 測試 有兩個node且為右子樹關係的AVL Tree 是否也能順利執行postorder
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert兩個元素以進行驗證
		avlTree.insert(2);
		avlTree.insert(3);
		
		// when 
		// 使用postorder時是否能成功運作
		String postorderResult = avlTree.postorder() ;							
		
		// then
		// 輸出上因為AVL Tree中元素為2,3，所以這邊應該是要輸出3,2	
		String expectPostorder ="3 2";
		
		assertEquals(expectPostorder, postorderResult);
	}
	
	@Test
	void postorder_3nodesAVLTree_132_byPartition() {
		AvlTree avlTree = new AvlTree();
		
		// given 
		// insert三個元素，稍早有驗證過rotate在insert時正常執行，所以這裡可以不用考慮rotate，進行驗證
		avlTree.insert(1);
		avlTree.insert(3);
		avlTree.insert(2);
		
		// when 
		// 使用postorder時是否能成功運作
		String postorderResult = avlTree.postorder() ;		
		
		// then
		// 輸出上因為AVL Tree中元素為1,3,2，所以這邊應該是要輸出1,3,2	
		String expectPostorder ="1 3 2";
		
		assertEquals(expectPostorder, postorderResult);
	}
	
	
	/*
	 *  Boundary Test
	 */
	// 測試內容：持續insert Ｎ個 再makeEmpty
	// time: 18s
	@Test
	void insertBoundaryTest_ManyInserts_NULL_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given
		// 新增 Ingeger.MAX_VALUE次1進去
		// 最後並且一次清空
		for(int i=0 ;i<Integer.MAX_VALUE;i++) {
			avlTree.insert(1);
		}
		avlTree.makeEmpty();
		
		// when
		// 希望能成功建立大量的元素後，又成功刪除
		boolean resultEmpty = avlTree.isEmpty();
		
		// then 
		// 預期能順利清空，回傳true
		boolean expectedEmpty = true;
		assertEquals(expectedEmpty, resultEmpty);
	}
	//add 很大或很小的元素
	@Test
	void insertBoundaryTest_MAXnMin_6_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		// given
		// 塞入很大的數以及很小的數當作元素
		avlTree.insert(Integer.MAX_VALUE-1);
		avlTree.insert(Integer.MAX_VALUE);
		avlTree.insert(Integer.MAX_VALUE-2);
		
		avlTree.insert(Integer.MIN_VALUE+1);
		avlTree.insert(Integer.MIN_VALUE);
		avlTree.insert(Integer.MIN_VALUE+2);
		
		// when
		// 透過countNodes驗證是否有順利建立
		int resultCount = avlTree.countNodes();
		
		// then 
		// 正常來說應該要全部都順利insert，所以這邊應回傳6
		int expectedCount = 6;
		assertEquals(expectedCount, resultCount);
	}
	
	
	/*
	 * Negative Test
	 */
	// 測試：當元素已經為空的時候，makeEmpty持續進行刪除時，會不會有錯誤
	// time: 1.172s
	@Test
	void makeEmptyNegativeTest_ContinuousMakeEmpty_true_byCoverage() {
		AvlTree avlTree = new AvlTree();
		// given
		// 本身沒有再新增元素，並直接開始Integer.MAX_VALUE次的makeEmpty，不斷進行清空
		for(int i=0 ;i<Integer.MAX_VALUE;i++) {
			avlTree.makeEmpty();
		}
		
		
		// when 
		// 執行無數次清空後，檢查是否為空
		
		boolean resultEmpty = avlTree.isEmpty();
		
		// then
		// 理應上，還是要保持為空的狀態
		boolean expectedEmpty = true;
		
		assertEquals(expectedEmpty, resultEmpty);
	}
	
	
	
	/*
	 *  Performance test
	 */
	// 測試：使用一個超大的Tree進行測試，並計算時間與元素個數間是否呈現線性關聯，以符合 O(log n) 時間複雜度
	@Test
	void performanceTest_BigAVLTree_Ologn_byCoverage() {
		
		
		// given 
		// saveNodeCount_1 與 saveNodeCount_2 中 分別給予2^20次方與2^19次方的值
		// 後續建兩棵AVL Tree 時，直接將0~2^20-1 與0~2^19-1分別建入 avlTree 與 avlTree1中
		// 這個insert的過程分別使用 System.currentTimeMillis()，以進行耗時紀錄
		double saveNodeCount_1 = Math.pow(2.0,20.0);
		
		
		long start = System.currentTimeMillis();
		AvlTree avlTree = new AvlTree();
		for(int i=0 ; i<saveNodeCount_1 ;i++) {
			avlTree.insert(i);
		}
		long end = System.currentTimeMillis();
		
		double saveNodeCount_2 = Math.pow(2.0,19.0);
		
		long start1 = System.currentTimeMillis();
		AvlTree avlTree1 = new AvlTree();
		for(int i=0 ; i<saveNodeCount_2;i++) {
			avlTree1.insert(i);
		}
		long end1 = System.currentTimeMillis();
		
		
		double rate = ((end - start)/((end1 - start1)));
		
		// when 
		// 進行比對兩棵樹建立的時間成長與為 N 為 2^20/2^19 這個個數差距成長關係是否為O(log n) 的關係
		
		double result =  Math.pow(2,(Math.log(saveNodeCount_1/saveNodeCount_2) / Math.log(2)));
		
		boolean compareResult = (rate <= result);
		// then
		// 根據定義 insert 在AVL Tree的執行中應該要是O(log n)，以這個例子來說兩個執行的時間差距應該要在 2^1 以內，因為是 big-O 所以是<=
		// 預期會比時間複雜度上限小，所以為傳true
		// 並於最後輸出相關的執行時間與比例
		boolean expected = true;
		
		
		System.out.println("DEBUG: Insert 2^20 items took " + (end - start)/1000.0 + " Seconds");
		System.out.println("DEBUG: Insert 2^19 items took " + (end1 - start1)/1000.0 + " Seconds");
		System.out.println("DEBUG: " + rate );

		assertEquals(expected,compareResult);
	}
	
    /*
     *  partial oracle
     */
	
	// 上述執行單元測試完後都正確，這邊希望使用這些功能進行一連串的測試，假設建立在前面是對的情況下進行未知結果的測試來得到pass或fail
	@Test
	void partialOracle_FullyAVLTreeAndSerialTesting_Pass_byCoverage() {
		AvlTree avlTree = new AvlTree();
		
		boolean resultIsEmptyBefore = avlTree.isEmpty();
		
		// given
		// 提供測資組合為1~10中依序交錯一大一小一大一小這樣排列，並在前後以及中間插入重複的輸入，以用來驗證整個AVL Tree的完整正確度
		avlTree.insert(10);
		avlTree.insert(10);
		avlTree.insert(1);
		avlTree.insert(9);
		avlTree.insert(2);
		avlTree.insert(8);
		avlTree.insert(3);
		avlTree.insert(8);
		avlTree.insert(7);
		avlTree.insert(4);
		avlTree.insert(6);
		avlTree.insert(5);
		avlTree.insert(5);
		
		// when
		// 以下一連串AVL Tree的相關操作，並將 public的method盡可能使用到進行測試
		int resultCountNodesNow = avlTree.countNodes();
		boolean resultSearch_seven = avlTree.search(7);
		boolean resultSearch_eleven =  avlTree.search(11);
		
		String resultOutputInorder = avlTree.inorder();
		String resultOutputPreorder = avlTree.preorder();
		String resultOutputPostorder = avlTree.postorder();
		
		avlTree.makeEmpty();
		int resultCountNodesAfter = avlTree.countNodes();
		boolean resultIsEmptyAfter = avlTree.isEmpty();
		
		
		// then
		// 進行上述測試以後的相關預期答案希望我們的程式能正確地執行
		boolean expectedIsEmptyBefore = true;
		int expectedCountNodesNow = 10;
		
		boolean expectedSearch_seven = true;
		boolean expectedSearch_eleven =  false;
		
		String expectedOutputInorder ="1 2 3 4 5 6 7 8 9 10";
		String expectedOutputPreorder = "8 4 2 1 3 6 5 7 9 10";
		String expectedOutputPostorder ="1 3 2 5 7 6 4 10 9 8";
		
		
		int expectedCountNodesAfter = 0;
		boolean expectedIsEmptyAfter = true;
		assertEquals(expectedIsEmptyBefore,resultIsEmptyBefore);
		assertEquals(expectedCountNodesNow,resultCountNodesNow);
		assertEquals(expectedSearch_seven,resultSearch_seven);
		assertEquals(expectedSearch_eleven,resultSearch_eleven);
		assertEquals(expectedOutputInorder,resultOutputInorder);
		assertEquals(expectedOutputPreorder,resultOutputPreorder);
		assertEquals(expectedOutputPostorder,resultOutputPostorder);
		assertEquals(expectedCountNodesAfter,resultCountNodesAfter);
		assertEquals(expectedIsEmptyAfter,resultIsEmptyAfter);
		

		
	}
}
