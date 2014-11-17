%%Data is downloaded from http://www.ee.cuhk.edu.hk/~xgwang/MITtrajsingle.html
%%The file name is "filtTrk_parkinglot.mat"
%%There are 40453 tracks in the file

data = load('filtTrk_parkinglot');
fileID = fopen('subTracks', 'w');
seg = 20;
numOfSegs = 0;
for i = 1:length(data.trk)
    subCounter = 1;
    counter = seg;
    for j = 1:length(data.trk(1,i).x(1,:))
        
        if counter == seg
            counter = 0;
            fprintf(fileID, '%c', '<');
            fprintf(fileID, '%d', i);
            fprintf(fileID, '%c', '-');
            fprintf(fileID, '%d', subCounter);
            fprintf(fileID, '%c\n', '>');
            subCounter = subCounter+1;
            numOfSegs=numOfSegs+1;
        end 
        fprintf(fileID, '%c', '(');
        fprintf(fileID, '%d', data.trk(1,i).x(1,j));
        fprintf(fileID, '%c', ',');
        fprintf(fileID, '%d', data.trk(1,i).y(1,j));
        fprintf(fileID, '%c', ')');
        counter = counter + 1;
        
        if counter == seg || j == length(data.trk(1,i).x(1,:))
            fprintf(fileID, '%c\n', '');
        else
            fprintf(fileID, '%c', ';');
        end
    end
end
fclose(fileID);
fprintf('Number of trajectories is %i\n', i);
fprintf('Number of subtrajectories is %i\n', numOfSegs);
%%40453 tracks used
%%240242 subtracks generated