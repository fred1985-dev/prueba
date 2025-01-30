import { Component, OnInit } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import { FlatTreeControl } from '@angular/cdk/tree';
import { MatTreeFlattener, MatTreeFlatDataSource } from '@angular/material/tree';

// Clase que representa un nodo con estructura jerárquica
export class TodoItemNode {
  children: TodoItemNode[] = []; // Lista de hijos del nodo
  item: string = ''; // Nombre o título del nodo
}

// Clase que representa un nodo plano para la visualización en el árbol
export class TodoItemFlatNode {
  item: string = ''; // Nombre o título del nodo
  level: number = 0; // Nivel del nodo en el árbol
  expandable: boolean = false; // Indica si el nodo tiene hijos
}

// Datos iniciales para el árbol
const TREE_DATA = {
  Groceries: {
    'Almond Meal flour': null,
    'Organic eggs': null,
    'Protein Powder': null,
    Fruits: {
      Apple: null,
      Berries: {
        Blueberry: null,
        Raspberry: null,
      },
      Orange: null,
    },
  },
  Reminders: [
    'Cook dinner',
    'Read the Material Design spec',
    'Upgrade Application to Angular',
  ],
};

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrls: ['./rol.component.css'],
})
export class RolComponent implements OnInit {
  // Controlador del árbol para gestionar nodos planos
  treeControl: FlatTreeControl<TodoItemFlatNode>;
  titulo: string = 'Crear Rol';
  // Flattener que convierte nodos jerárquicos en nodos planos
  treeFlattener: MatTreeFlattener<TodoItemNode, TodoItemFlatNode>;

  // Fuente de datos del árbol
  dataSource: MatTreeFlatDataSource<TodoItemNode, TodoItemFlatNode>;

  // Modelo de selección para los checkboxes
  checklistSelection = new SelectionModel<TodoItemFlatNode>(true /* multiple */);

  constructor() {
    // Configuración del flattener y el treeControl
    this.treeFlattener = new MatTreeFlattener(
      this.transformer, // Convierte nodos
      this.getLevel, // Obtiene el nivel del nodo
      this.isExpandable, // Indica si es expandible
      this.getChildren // Obtiene hijos del nodo
    );
    this.treeControl = new FlatTreeControl<TodoItemFlatNode>(
      this.getLevel,
      this.isExpandable
    );
    this.dataSource = new MatTreeFlatDataSource(
      this.treeControl,
      this.treeFlattener
    );

    // Inicializamos la estructura del árbol
    this.dataSource.data = this.buildFileTree(TREE_DATA, 0);
  }

  ngOnInit(): void {}

  // Convierte un nodo jerárquico en un nodo plano
  transformer = (node: TodoItemNode, level: number) => {
    const flatNode = new TodoItemFlatNode();
    flatNode.item = node.item;
    flatNode.level = level;
    flatNode.expandable = !!node.children?.length; // Es expandible si tiene hijos
    return flatNode;
  };

  // Obtiene el nivel de un nodo
  getLevel = (node: TodoItemFlatNode) => node.level;

  // Indica si un nodo es expandible
  isExpandable = (node: TodoItemFlatNode) => node.expandable;

  // Devuelve los hijos de un nodo
  getChildren = (node: TodoItemNode): TodoItemNode[] => node.children;

  // Verifica si un nodo tiene hijos
  hasChild = (_: number, node: TodoItemFlatNode) => node.expandable;

  // Construye la estructura jerárquica del árbol a partir de un objeto
  buildFileTree(obj: { [key: string]: any }, level: number): TodoItemNode[] {
    return Object.keys(obj).reduce<TodoItemNode[]>((accumulator, key) => {
      const value = obj[key];
      const node = new TodoItemNode();
      node.item = key;

      if (value != null) {
        if (typeof value === 'object') {
          node.children = this.buildFileTree(value, level + 1);
        } else if (Array.isArray(value)) {
          node.children = value.map((item) => ({
            item: item,
            children: [],
          }));
        }
      }
      return accumulator.concat(node);
    }, []);
  }

  // Maneja la selección de nodos con checkboxes
  todoItemSelectionToggle(node: TodoItemFlatNode): void {
    this.checklistSelection.toggle(node);

    const descendants = this.treeControl.getDescendants(node);
    this.checklistSelection.isSelected(node)
      ? this.checklistSelection.select(...descendants)
      : this.checklistSelection.deselect(...descendants);

    this.checkAllParentsSelection(node);
  }

  // Verifica si todos los descendientes están seleccionados
  descendantsAllSelected(node: TodoItemFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    return descendants.every((child) =>
      this.checklistSelection.isSelected(child)
    );
  }

  // Verifica si algunos descendientes están seleccionados
  descendantsPartiallySelected(node: TodoItemFlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const result = descendants.some((child) =>
      this.checklistSelection.isSelected(child)
    );
    return result && !this.descendantsAllSelected(node);
  }

  // Verifica y ajusta la selección de nodos padres
  checkAllParentsSelection(node: TodoItemFlatNode): void {
    let parent: TodoItemFlatNode | null = this.getParentNode(node);
    while (parent) {
      this.checkRootNodeSelection(parent);
      parent = this.getParentNode(parent);
    }
  }

  // Ajusta la selección de nodos raíz
  checkRootNodeSelection(node: TodoItemFlatNode): void {
    const nodeSelected = this.checklistSelection.isSelected(node);
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.every((child) =>
      this.checklistSelection.isSelected(child)
    );

    if (nodeSelected && !descAllSelected) {
      this.checklistSelection.deselect(node);
    } else if (!nodeSelected && descAllSelected) {
      this.checklistSelection.select(node);
    }
  }

  // Obtiene el nodo padre de un nodo actual
  getParentNode(node: TodoItemFlatNode): TodoItemFlatNode | null {
    const currentLevel = this.getLevel(node);
    if (currentLevel < 1) {
      return null;
    }

    const startIndex = this.treeControl.dataNodes.indexOf(node) - 1;
    for (let i = startIndex; i >= 0; i--) {
      const currentNode = this.treeControl.dataNodes[i];
      if (this.getLevel(currentNode) < currentLevel) {
        return currentNode;
      }
    }
    return null;
  }
}
