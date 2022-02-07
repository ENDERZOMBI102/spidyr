//@ts-ignore: deno lang server not working on vsc
import * as stringParser from "./src/stringparser.ts";
//@ts-ignore: deno lang server not working on vsc
import * as tokenizer from "./src/tokenizer.ts";

//@ts-ignore: deno lang server not working on vsc
const source = await (Deno.readTextFile("./test/main.dyr"));
console.log(source + "\n\n");
const parsed = stringParser.parseString(source);
console.log(parsed);
console.log("\n");
const tokens = tokenizer.tokenize(parsed);
console.log(tokens);
console.log("\n");