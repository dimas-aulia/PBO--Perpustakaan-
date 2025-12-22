#include <iostream>
using namespace std;

// Struktur node BST
struct Node {
    int data;
    Node* left;
    Node* right;
};

// Membuat node baru
Node* createNode(int value) {
    Node* newNode = new Node;
    newNode->data = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

// Fungsi insert ke BST
Node* insertBST(Node* root, int value) {
    // Jika pohon kosong
    if (root == NULL) {
        return createNode(value);
    }

    // Jika nilai lebih kecil, masuk ke kiri
    if (value < root->data) {
        root->left = insertBST(root->left, value);
    }
    // Jika nilai lebih besar, masuk ke kanan
    else if (value > root->data) {
        root->right = insertBST(root->right, value);
    }

    return root;
}

// Traversal inorder (kiri - root - kanan)
void inorder(Node* root) {
    if (root != NULL) {
        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }
}

int main() {
    Node* root = NULL;

    int data[] = {64, 60, 86, 92, 30, 95, 50, 13, 38, 65, 35, 40, 96, 80, 21};
    int n = sizeof(data) / sizeof(data[0]);

    // Insert data ke BST
    for (int i = 0; i < n; i++) {
        root = insertBST(root, data[i]);
    }

    // Tampilkan hasil inorder
    cout << "Traversal Inorder BST: ";
    inorder(root);

    return 0;
}
