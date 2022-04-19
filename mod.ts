import * as stringParser from "./stringparser.ts";
import * as tokenizer from "./tokenizer.ts";
import * as syntaxTree from "./syntaxtree.ts";

const source = await (Deno.readTextFile("./test/main.dyr"));
console.log(source + "\n\n");
const parsed = stringParser.parseString(source);
console.log(parsed);
console.log("\n");
const tokens = tokenizer.tokenize(parsed);
console.log(tokens);
console.log("\n");
const syntaxtree = syntaxTree.toSyntaxTree(tokens);
console.log(syntaxtree);
console.log("\n");