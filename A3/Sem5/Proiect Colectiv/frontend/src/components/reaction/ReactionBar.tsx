import {ReactionStats, ReactionType} from "../../model/reaction.ts";
import {useState} from "react";
import {Badge, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAngry, faFaceSadTear, faHeart, faThumbsUp} from "@fortawesome/free-solid-svg-icons";
import {IconDefinition} from "@fortawesome/fontawesome-svg-core";

interface ReactionBarProps {
    reactions: ReactionStats;
    interactive: boolean;
    onReact: (reaction: ReactionType | null, update?: boolean) => void;
}

function getIconForReaction(reactionType: ReactionType): IconDefinition {
    switch (reactionType) {
        case "LOVE":
            return faHeart;
        case "LIKE":
            return faThumbsUp;
        case "SAD":
            return faFaceSadTear;
        case "ANGRY":
            return faAngry;
    }
}

export function ReactionBar({reactions, interactive, onReact}: ReactionBarProps) {
    const selectedUserReaction = reactions.find(reaction => reaction.userReacted)?.type ?? null;
    const [selectedReaction, setSelectedReaction] = useState<ReactionType | null>(selectedUserReaction);

    const handleReaction = (reaction: ReactionType) => {
        if (selectedReaction === reaction) {
            setSelectedReaction(null);
            onReact(null);
        } else {
            setSelectedReaction(reaction);
            onReact(reaction, selectedUserReaction !== null);
        }
    };

    return (
        <div className="d-flex gap-2">
            {reactions.map(({type, count}) => (
                <Button
                    key={type}
                    variant={interactive && selectedReaction === type ? "primary" : "light"}
                    disabled={!interactive}
                    className="d-flex align-items-center"
                    onClick={(e) => {
                        e.stopPropagation();
                        handleReaction(type);
                    }}
                >
                    <FontAwesomeIcon icon={getIconForReaction(type)}/>
                    <span >
                        { count > 0 &&
                            <Badge bg="secondary" className="ms-2">{count}</Badge>
                        }
                    </span>
                </Button>
            ))}
        </div>
    );
}
