# A sample Guardfile
# More info at https://github.com/guard/guard#readme

## Uncomment and set this to only include directories you want to watch
# directories %w(app lib config test spec features) \
#  .select{|d| Dir.exists?(d) ? d : UI.warning("Directory #{d} does not exist")}

## Note: if you are using the `directories` clause above and you are not
## watching the project directory ('.'), then you will want to move
## the Guardfile to a watched dir and symlink it back, e.g.
#
#  $ mkdir config
#  $ mv Guardfile config/
#  $ ln -s config/Guardfile .
#
# and, you'll have to watch "config/Guardfile" instead of "Guardfile"

# Add files and commands to this file, like the example:
#   watch(%r{file/path}) { `command(s)` }
#
# find . -name "*.java" -print | xargs javac -g
# find . -name '*.class' -exec cp --parents \{\} ../bin \;
# find . -name "*.class" -type f -delete
guard :shell do
  #watch(/src\/(.*).java/) {|m| `tail #{m[0]}` }
  #watch(/src\/(.*).java/) {|m| `./build.sh > out.txt 2>&1 && java -ea -cp bin Solver >> out.txt 2>&1` }
	watch( /src\/((.*).java)/ ) do |matched| # {|m| `./build.sh > out.txt 2>&1 && java -ea -cp bin Solver >> out.txt 2>&1` }
		rel_path = matched[ 1 ]
		Dir.chdir 'src'
		`echo "" > ../out.txt`
		`javac -g -d ../bin #{ rel_path } >> ../out.txt 2>&1`
		Dir.chdir '..'
		`java -ea -cp bin Solver >> out.txt 2>&1`
	end
end
