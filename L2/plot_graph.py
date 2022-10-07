import csv
import matplotlib.pyplot as plt
import networkx as nx

G = nx.Graph()

with open("./data/graph.csv") as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=",")
    for row in csv_reader:
        G.add_edge(row[0], row[1], weight=float(row[2]))

elarge = [(u, v) for (u, v, d) in G.edges(data=True) if d["weight"] > 0.5]
esmall = [(u, v) for (u, v, d) in G.edges(data=True) if d["weight"] <= 0.5]

pos = nx.spring_layout(G, seed=7)  # positions for all nodes - seed for reproducibility

# nodes
# nx.draw(G, with_labels=False)
nx.draw_networkx_nodes(G, pos, node_size=400)

# edges
nx.draw_networkx_edges(G, pos, edgelist=elarge, width=0.5, arrows=True, arrowsize=14, arrowstyle='-|>')
nx.draw_networkx_edges(
    G, pos, edgelist=esmall, width=10.5, alpha=0.5, edge_color="b", style="dashed", arrows=True, arrowsize=10, arrowstyle='-|>'
)

# node labels
nx.draw_networkx_labels(G, pos, font_size=20, font_family="sans-serif")
# edge weight labels
# edge_labels = nx.get_edge_attributes(G, "weight")
# nx.draw_networkx_edge_labels(G, pos, edge_labels)

ax = plt.gca()
ax.margins(0.08)
plt.axis("off")
plt.tight_layout()
plt.show()
