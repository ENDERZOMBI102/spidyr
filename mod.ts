import * as stringParser from "./src/stringparser.ts";
import * as tokenizer from "./src/tokenizer.ts";
import * as syntaxTree from "./src/syntaxtree.ts";

const source = await (Deno.readTextFile("./test/main.dyr"));
console.log(source + "\n\n");
const parsed = stringParser.parseString(source);
console.log(parsed);
console.log("\n");
const tokens = tokenizer.tokenize(parsed);
console.log(tokens);
console.log("\n");
const syntaxtree = syntaxTree.toSyntaxTree(tokens);